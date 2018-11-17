package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.Main;
import org.njarasoa.fifidianana.util.IdValuePair;

import java.util.List;
import java.util.stream.Collectors;

public class ToeramPifidianana extends IdValuePair {
    private List<EfitraFifidianana> efitraFifidiananaList;
    private Fokontany fokontany;

    public ToeramPifidianana(String _string, Fokontany _fokontany) {
        super(_string);
        fokontany = _fokontany;
        efitraFifidiananaList = retrieveEfitraFifidiananaList(id);
    }

    public List<EfitraFifidianana> getEfitraFifidiananaList() {
        return efitraFifidiananaList;
    }

    private static final String URL = "https://presidentielle.ceni-madagascar.mg/bv/chargebv/";

    private List<EfitraFifidianana> retrieveEfitraFifidiananaList(String id) {
        List<String> strs = Main.getURL(URL + id);

        List<EfitraFifidianana> efs = strs.parallelStream().map(i -> new EfitraFifidianana(i, this)).collect(Collectors.toList());

        return efs;
    }

    public String anaranaFeno() {
        return (fokontany.anaranaFeno()
                + Main.SEPARATOR
                + anarana).trim();
    }
}
