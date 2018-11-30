package org.njarasoa.mpamakyPdf;

import org.njarasoa.mpamakyPdf.model.Valimpifidianana;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mpikaroka {
    public static List<String> findPdfs(String _initialPath) throws NullPointerException {
        List<String> names = new ArrayList<>();
        findPdfs(new File(_initialPath), names);
        return names;
    }

    private static void findPdfs(File _f, List<String> _names) throws NullPointerException {

        if (_f.isDirectory()) {
            String[] children = _f.list();
            for (int i = 0; i < children.length; i++) {
                findPdfs(new File(_f, children[i]), _names);
            }
        }
        if (_f.isFile() && _f.getName().endsWith(".pdf")) {
            _names.add(_f.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        String startingPoint = ".";
        if (args.length > 0) {
            startingPoint = args[0];
        }

        long start = System.currentTimeMillis();
        List<String> filenames = findPdfs(startingPoint);
        long end = System.currentTimeMillis();

        List<Valimpifidianana> valiny = new ArrayList<>();

        long start1 = System.currentTimeMillis();
        filenames.parallelStream().forEach(fname -> MpamakyPdf.parsePdf(fname, valiny));
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        save(valiny);
        long end2 = System.currentTimeMillis();

        System.out.println("It took " + (end - start) + "ms to find " + filenames.size() + " PDFs.");
        System.out.println("It took " + (end1 - start1) + "ms to parse PDFs.");
        System.out.println("It took " + (end2 - start2) + "ms to parse write out " + valiny.size() + " the results.");
    }

    private static void save(List<Valimpifidianana> valiny) {
        valiny.parallelStream().forEach(v -> System.out.println(v.toCsv()));
    }
}
