package org.njarasoa.fifidianana.valimpifidianana;

import org.njarasoa.fifidianana.valimpifidianana.model.FileDefinition;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class ValimpifidiananaProcessor {
    public static void main(String[] args) {
        List<FileDefinition> defs = new ArrayList<>();

        long start = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    defs.add(parseLine(line));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("BAD:" + line);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        defs.parallelStream().forEach(fd -> getPdf(fd));

        long end = System.currentTimeMillis();

        System.out.println(defs.size() + " records processed in " + (end - start) + "ms.");
        System.out.println("I spent " + totalIOtime + "ms on IO");
    }

    private static long totalIOtime = 0;

    private static void getPdf(FileDefinition _fd) {
        long start = System.currentTimeMillis();
        new File(_fd.getFilepath()).mkdirs();

        try {
            URL website = new URL(_fd.getRemotePath());
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = null;
            fos = new FileOutputStream(_fd.getFullFilePath());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            System.out.println("Problem processing " + _fd + ". Exception was [" + e.getMessage() + "]");
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        totalIOtime += end - start;
        System.out.println(_fd.getFullFilePath() + " complete.");
    }

    private static FileDefinition parseLine(String _str) {
//        System.out.println(_str);
        String[] strs = _str.split("\\^");
        String biraoId = strs[1];
        String faritra = biraoId.substring(0, 2) + "-" + cleanName(strs[2]);
        String fivondronana = biraoId.substring(0, 4) + "-" + cleanName(strs[3]);
        String firaisana = biraoId.substring(0, 6) + "-" + cleanName(strs[4]);
        String fokontany = biraoId.substring(0, 8) + "-" + cleanName(strs[5]);
        String birao = biraoId.substring(0, 10) + "-" + cleanName(strs[6]);
        String efitra = cleanName(strs[6]);
        if (strs.length == 8) {
            efitra = cleanName(strs[7]);
        }

        String fPath = String.join("/", faritra, fivondronana, firaisana, fokontany, birao);
        String fName = biraoId + "-" + efitra + ".pdf";

        return new FileDefinition(biraoId, fPath, fName);
    }

    private static String cleanName(String _str)
    {
        return _str.replace("/", "_")
            .replace("\\", "_")
            .replace("^", "_")
            .replace("*", "_")
            .replace("?", "_");
    }
}
