package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

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

    private List<ToeramPifidianana> retrieveToeramPifidiananaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.TOERAMPIFIDIANANA_URL + id);

        List<ToeramPifidianana> tps = strs.parallelStream().map(i -> new ToeramPifidianana(i, this)).collect(Collectors.toList());

        return tps;
    }

    public String anaranaFeno() {
        return (kaominina.anaranaFeno()
                + ValimpifidiananaProcessor.SEPARATOR
                + anarana).trim();
    }
}
