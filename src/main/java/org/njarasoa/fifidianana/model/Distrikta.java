package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

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

    private List<Kaominina> retrieveKaomininaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.KAOMININA_URL + id);

        List<Kaominina> ks = strs.parallelStream().map(i -> new Kaominina(i, this)).collect(Collectors.toList());

        return ks;
    }

    public String anaranaFeno() {
        return faritra.getAnarana()
                + ValimpifidiananaProcessor.SEPARATOR
                + anarana;
    }
}
