package org.njarasoa.mpamakyPdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.njarasoa.mpamakyPdf.model.Valimpifidianana;
import org.njarasoa.mpamakyPdf.model.Valimpifidianana.ValimpifidiananaBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MpamakyPdf {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("We are dead!");
        } else {
            List<Valimpifidianana> valiny = new ArrayList<>();
            Arrays.asList(args).stream().forEach(fname -> parsePdf(fname, valiny));
            save(valiny);
        }
    }

    private static void save(List<Valimpifidianana> valiny) {
    }

    public static Valimpifidianana parsePdf(String _fname, List<Valimpifidianana> _valiny) {
        File file = new File(_fname);
        String efitra = file.getName();
        String birao = file.getParentFile().getName();
        String fokontany = file.getParentFile().getParentFile().getName();
        String firaisana = file.getParentFile().getParentFile().getParentFile().getName();
        String fivondronana = file.getParentFile().getParentFile().getParentFile().getParentFile().getName();
        String faritra = file.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getName();
        // System.out.println("==========" + efitra);

        ValimpifidiananaBuilder b = new ValimpifidiananaBuilder();

        b.withFaritra(faritra)
                .withFivondronana(fivondronana)
                .withFiraisana(firaisana)
                .withFokontany(fokontany)
                .withBirao(birao)
                .withEfitra(efitra);

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

                List<String> lines = Arrays.asList(text.split("\n"));

                for (String line : lines) {
                    processLine(line, b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Valimpifidianana v = b.build();
        _valiny.add(v);
        return v;
    }

    private static void processLine(String _line, ValimpifidiananaBuilder _builder) {
        if (_line.contains("Ce bureau de vote n'est pas encore traité"))
        {
            _builder.withNote(_line.trim());
        }
        if (_line.startsWith("Inscrits")) {
            String[] vals = _line.split(":");
            _builder.withNisotratraAnarana(Long.parseLong(vals[1].trim()));
        } else if (_line.startsWith("Blancs et Nuls")) {
            String[] vals = _line.split(":");
            String[] vals2 = vals[1].trim().split(" ");
            _builder.withVatoMaty(Long.parseLong(vals2[0].trim()));
        } else if (!_line.contains(":")
                && ((_line.startsWith("1")
                || _line.startsWith("2")
                || _line.startsWith("3")
                || _line.startsWith("4")
                || _line.startsWith("5")
                || _line.startsWith("6")
                || _line.startsWith("7")
                || _line.startsWith("8")
                || _line.startsWith("9")
                || _line.startsWith("0")))) {
            // process result
            parseKandidaValiny(_line, _builder);
        } else {
            // System.out.println("-" + _line);
        }
    }

    private static void parseKandidaValiny(String s, ValimpifidiananaBuilder _builder) {
        Matcher m = p.matcher(s.trim());
        if (m.find()) {
            int laharana = Integer.parseInt(m.group(1).trim());
            String anarana = m.group(2).trim();
            long vatoAzo = Long.parseLong(m.group(3).trim());
            _builder.withVatoAzo(laharana, vatoAzo);
        }
    }

    private static final Pattern p = Pattern.compile("([0-9]{1,2})([A-Za-z ]+)([0-9]{1,8})");

}
