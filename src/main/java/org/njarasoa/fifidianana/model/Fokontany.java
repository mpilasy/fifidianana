package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.Main;
import org.njarasoa.fifidianana.util.IdValuePair;

import java.util.List;
import java.util.stream.Collectors;

public class Fokontany extends IdValuePair {
    private List<ToeramPifidianana> toeramPifidiananaList;
    private Kaominina kaominina;

    public Fokontany(String _string, Kaominina _kaominina) {
        super(_string);
        kaominina = _kaominina;
        toeramPifidiananaList = retrieveToeramPifidiananaList(id);
    }

    public List<ToeramPifidianana> getToeramPifidiananaList() {
        return toeramPifidiananaList;
    }

    private static final String URL = "https://presidentielle.ceni-madagascar.mg/cv/chargecv/";
    private List<ToeramPifidianana> retrieveToeramPifidiananaList(String id) {
        List<String> strs = Main.getURL(URL + id);

        List<ToeramPifidianana> tps = strs.parallelStream().map(i -> new ToeramPifidianana(i, this)).collect(Collectors.toList());

        return tps;
    }

    public String anaranaFeno() {
        return (kaominina.anaranaFeno()
                + Main.SEPARATOR
                + anarana).trim();
    }
}
