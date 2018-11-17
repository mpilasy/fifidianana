package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fokontany extends IdValuePair {
    private List<ToeramPifidianana> toeramPifidiananaList;
    private Firaisana parent;

    Fokontany(String _string, Firaisana _firaisana) {
        super(_string);
        parent = _firaisana;
        toeramPifidiananaList = retrieveToeramPifidiananaList(id);
    }

    public List<ToeramPifidianana> getToeramPifidiananaList() {
        return toeramPifidiananaList;
    }

    private List<ToeramPifidianana> retrieveToeramPifidiananaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.TOERAMPIFIDIANANA_URL + id);

        return strs
                .parallelStream()
                .map(i -> new ToeramPifidianana(i, this))
                .collect(Collectors.toList());
    }

    String anaranaFeno() {
        return MessageFormat.format("{0}{1}{2}", parent.anaranaFeno(), ValimpifidiananaProcessor.SEPARATOR, anarana).trim();
    }

    List<EfitraFifidianana> getEfitraFifidiananaList()
    {
        List<EfitraFifidianana> efitraList = new ArrayList<>();
        for (ToeramPifidianana toerana: toeramPifidiananaList)
        {
            efitraList.addAll(toerana.getEfitraFifidiananaList());
        }
        return efitraList;
    }
}
