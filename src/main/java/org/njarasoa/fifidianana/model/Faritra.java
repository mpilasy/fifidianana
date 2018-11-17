package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.util.*;
import java.util.stream.Collectors;

public class Faritra extends IdValuePair {

    private List<Distrikta> distriktaList;


    public Faritra(String _string) {
        super(_string);
        distriktaList = retrieveDistriktaList(id);
    }

    public List<Distrikta> getDistriktaList() {
        if (distriktaList == null) {
            distriktaList = retrieveDistriktaList(id);
        }
        return distriktaList;
    }

    private List<Distrikta> retrieveDistriktaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.DISTRIKTA_URL + id);

        List<Distrikta> ds = strs.parallelStream().map(i -> new Distrikta(i, this)).collect(Collectors.toList());

        return ds;
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
