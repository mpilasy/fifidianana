package org.njarasoa.fifidianana.rafitra.model;

import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Faritra extends IdValuePair {

    private List<Fivondronana> fivondronanaList;


    public Faritra(String _string) {
        super(_string);
        fivondronanaList = retrieveDistriktaList(id);
    }

    public List<Fivondronana> getFivondronanaList() {
        if (fivondronanaList == null) {
            fivondronanaList = retrieveDistriktaList(id);
        }
        return fivondronanaList;
    }

    private List<Fivondronana> retrieveDistriktaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.FIVONDRONANA_URL + id);

        return strs
                .parallelStream()
                .map(i -> new Fivondronana(i, this))
                .collect(Collectors.toList());
    }

    public List<EfitraFifidianana> getEfitraFifidiananaList()
    {
        List<EfitraFifidianana> efitraList = new ArrayList<>();
        for (Fivondronana fivondronana : fivondronanaList)
        {
            efitraList.addAll(fivondronana.getEfitraFifidiananaList());
        }
        return efitraList;
    }

    public static final List<String> AnaranaFaritraList = Arrays.asList(
            "11-ANALAMANGA",
            "12-BONGOLAVA",
            "13-ITASY",
            "14-VAKINANKARATRA",
            "21-DIANA",
            "22-SAVA",
            "31-AMORON'I MANIA",
            "32-ATSIMO-ATSINANANA",
            "33-HAUTE MATSIATRA",
            "34-IHOROMBE",
            "35-VATOVAVY-FITOVINANY",
            "41-BETSIBOKA",
            "42-BOENY",
            "43-MELAKY",
            "44-SOFIA",
            "51-ALAOTRA-MANGORO",
            "52-ANALANJIROFO",
            "53-ATSINANANA",
            "61-ANDROY",
            "62-ANOSY",
            "63-ATSIMO-ANDREFANA",
            "64-MENABE"
    );
}
