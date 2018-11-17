package org.njarasoa.fifidianana;

import org.njarasoa.fifidianana.model.Faritra;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValimpifidiananaProcessor {

    private static long count = 0;

    public synchronized static long getNextCount()
    {
        return ++count;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Faritra> faritraList = Faritra.AnaranaFaritraList
                .parallelStream()
                .map(i -> new Faritra(i))
                .collect(Collectors.toList());

        long end = System.currentTimeMillis();

        System.out.println("It took " + (end - start) + "ms to run he whole thing.");
        System.out.println("We found " + count + " efi-pifidianana.");
        System.out.println("We made " + timeLogs.size() + " calls to the server.");

    }

    // private static List<Fokontany> fokontanyList = new ArrayList<>();
    public static List<String> timeLogs = new ArrayList<String>();

    public static final String SEPARATOR = "^";
}
