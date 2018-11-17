package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.Main;
import org.njarasoa.fifidianana.util.IdValuePair;

import java.util.List;
import java.util.stream.Collectors;

public class Distrikta extends IdValuePair {
    private List<Kaominina> kaomininaList;
    private Faritra faritra;

    public Distrikta(String _string, Faritra _faritra) {
        super(_string);
        faritra = _faritra;
        kaomininaList = retrieveKaomininaList(id);
    }

    public List<Kaominina> getKaomininaList() {
        if (kaomininaList == null) {
            kaomininaList = retrieveKaomininaList(id);
        }
        return kaomininaList;
    }

    private static final String URL = "https://presidentielle.ceni-madagascar.mg/commune/chargecommune/";

    private List<Kaominina> retrieveKaomininaList(String id) {
        List<String> strs = Main.getURL(URL + id);

        List<Kaominina> ks = strs.parallelStream().map(i -> new Kaominina(i, this)).collect(Collectors.toList());

        return ks;
    }

    public String anaranaFeno() {
        return faritra.getAnarana()
                + Main.SEPARATOR
                + anarana;
    }
}
