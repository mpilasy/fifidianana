package org.njarasoa.mpamakyPdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MpamakyPdf {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("We are dead!");
        } else {
            Arrays.asList(args).stream().forEach(fname -> parsePdf(fname));
        }
    }

    private static void parsePdf(String _fname) {
        File file = new File(_fname);
        String efitra = file.getName();
        String birao = file.getParentFile().getName();
        String fokontany = file.getParentFile().getParentFile().getName();
        String firaisana = file.getParentFile().getParentFile().getParentFile().getName();
        String fivondronana = file.getParentFile().getParentFile().getParentFile().getParentFile().getName();
        String faritra = file.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getName();
        System.out.println("==========" + efitra);

        try (PDDocument document = PDDocument.load(file)) {
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!ap.canExtractContent()) {
                throw new IOException("You do not have permission to extract text");
            }

            PDFTextStripper stripper = new PDFTextStripper();

            // This example uses sorting, but in some cases it is more useful to switch it off,
            // e.g. in some files with columns where the PDF content stream respects the
            // column order.
            stripper.setSortByPosition(true);

            for (int p = 1; p <= document.getNumberOfPages(); ++p) {
                // Set the page interval to extract. If you don't, then all pages would be extracted.
                stripper.setStartPage(p);
                stripper.setEndPage(p);

                // let the magic happen
                String text = stripper.getText(document);

//                    // do some nice output with a header
//                    String pageStr = String.format("page %d:", p);
//                    System.out.println(pageStr);
//                    for (int i = 0; i < pageStr.length(); ++i) {
//                        System.out.print("-");
//                    }
//                    System.out.println("");

                List<String> a = Arrays.asList(text.split("\n"));

                // System.out.println(i + ":" + a[i]);
                a.stream().forEach(MpamakyPdf::processLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLine(String _s) {
        if (_s.startsWith("BV")) {
            // process BV
            System.out.println("BV: " + _s);
        } else if (_s.startsWith("Inscrits")) {
            // process misoratra anarana
            System.out.println("Voasoratra anarana: " + _s);
        } else if (_s.startsWith("Votants")) {
            // process mpifidy
            System.out.println("Nifidy: " + _s);
        } else if (_s.startsWith("Blancs et Nuls")) {
            // process vato maty sy tsy manakery
            System.out.println("Maty: " + _s);
        } else if (!_s.contains(":")
                && ((_s.startsWith("1")
                || _s.startsWith("2")
                || _s.startsWith("3")
                || _s.startsWith("4")
                || _s.startsWith("5")
                || _s.startsWith("6")
                || _s.startsWith("7")
                || _s.startsWith("8")
                || _s.startsWith("9")
                || _s.startsWith("0")))) {
            // process result
            parseKandidaValiny(_s);
        } else {
            System.out.println("-" + _s);
        }
    }

    private static void parseKandidaValiny(String s) {
        Matcher m = p.matcher(s.trim());
        if (m.find()) {
            int laharana = Integer.parseInt(m.group(1));
            String anarana = m.group(2).trim();
            long vatoAzo = Long.parseLong(m.group(3));
            System.out.println(MessageFormat.format("{0}:{1}:{2}", laharana, anarana, vatoAzo));
        }
    }

    private static final Pattern p = Pattern.compile("([0-9]{1,2})([A-Za-z ]+)([0-9]{1,8})");

}
