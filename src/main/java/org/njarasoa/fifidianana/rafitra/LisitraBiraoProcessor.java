package org.njarasoa.fifidianana.rafitra;

import org.njarasoa.fifidianana.rafitra.model.Faritra;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LisitraBiraoProcessor {

    private static long count = 0;

    public synchronized static long getNextCount()
    {
        return ++count;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Faritra> faritraList = Faritra.AnaranaFaritraList
                .parallelStream()
                .map(Faritra::new)
                .collect(Collectors.toList());

        long end = System.currentTimeMillis();

        System.out.println(MessageFormat.format("It took {0}ms to run he whole thing. {1}ms was spent waiting for IO",
                end - start,
                RemoteHelper.totalIOTime));
        System.out.println(MessageFormat.format("We found {0}({1}) efi-pifidianana.",
                count,
                faritraList.stream().mapToInt(f -> f.getEfitraFifidiananaList().size()).summaryStatistics().getSum()));
        System.out.println(MessageFormat.format("We made {0} calls to the server.", timeLogs.size()));
        RemoteHelper.errors.keySet().forEach(x -> System.out.println(x + " was received [" + RemoteHelper.errors.get(x) + "] times"));

    }

    // private static List<Fokontany> fokontanyList = new ArrayList<>();
    public static final List<String> timeLogs = new ArrayList<>();

    public static final String SEPARATOR = "^";
}
