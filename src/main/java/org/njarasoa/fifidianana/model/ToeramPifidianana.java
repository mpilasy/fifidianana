package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

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

    private List<EfitraFifidianana> retrieveEfitraFifidiananaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.EFITRA_FIFIDIANANA_URL + id);

        List<EfitraFifidianana> efs = strs
                .parallelStream()
                .map(i -> new EfitraFifidianana(i, this))
                .collect(Collectors.toList());

        return efs;
    }

    public String anaranaFeno() {
        return (fokontany.anaranaFeno()
                + ValimpifidiananaProcessor.SEPARATOR
                + anarana).trim();
    }
}
