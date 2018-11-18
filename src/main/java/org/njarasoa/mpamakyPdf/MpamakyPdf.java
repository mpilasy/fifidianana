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
        if (args.length != 1) {
            System.out.println("We are dead!");
        } else

            try (PDDocument document = PDDocument.load(new File(args[0]))) {
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

                    List<String> a = Arrays.asList(text.split("\n"));

                    // System.out.println(i + ":" + a[i]);
                    a.parallelStream().forEach(MpamakyPdf::processLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static void processLine(String s) {
        if (s.startsWith("BV")) {
            // process BV
        } else if (s.startsWith("Inscrits")) {
            // process misoratra anarana
        } else if (s.startsWith("Votants")) {
            // process mpifidy
        } else if (s.startsWith("Blancs et Nuls")) {
            // process vato maty sy tsy manakery
        } else if (!s.contains(":")
            && ((s.startsWith("1")
                || s.startsWith("2")
                || s.startsWith("3")
                || s.startsWith("4")
                || s.startsWith("5")
                || s.startsWith("6")
                || s.startsWith("7")
                || s.startsWith("8")
                || s.startsWith("9")
                || s.startsWith("0")))) {
            // process result
            parseKandidaValiny(s);
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
