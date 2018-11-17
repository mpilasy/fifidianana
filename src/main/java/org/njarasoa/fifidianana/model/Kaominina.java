package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.util.List;
import java.util.stream.Collectors;

public class Kaominina extends IdValuePair {
    private List<Fokontany> fokontanyList;
    private Distrikta distrikta;

    public Kaominina(String _string, Distrikta _distrikta) {
        super(_string);
        distrikta = _distrikta;
        fokontanyList = retrieveFokontanyList(id);
    }

    private List<Fokontany> retrieveFokontanyList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.FOKONTANY_URL + id);

        List<Fokontany> fs = strs.parallelStream().map(i -> new Fokontany(i, this)).collect(Collectors.toList());

        return fs;
    }

    public List<Fokontany> getFokontanyList() {
        return fokontanyList;
    }

    public String anaranaFeno() {
        return (distrikta.anaranaFeno()
                + ValimpifidiananaProcessor.SEPARATOR
                + anarana).trim();
    }
}
