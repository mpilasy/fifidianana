package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Firaisana extends IdValuePair {
    private final List<Fokontany> fokontanyList;
    private final Fivondronana parent;

    Firaisana(String _string, Fivondronana _fivondronana) {
        super(_string);
        parent = _fivondronana;
        fokontanyList = retrieveFokontanyList(id);
    }

    private List<Fokontany> retrieveFokontanyList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.FOKONTANY_URL + id);

        return strs
                .parallelStream()
                .map(i -> new Fokontany(i, this))
                .collect(Collectors.toList());
    }

    public List<Fokontany> getFokontanyList() {
        return fokontanyList;
    }

    String anaranaFeno() {
        return MessageFormat.format("{0}{1}{2}", parent.anaranaFeno(), ValimpifidiananaProcessor.SEPARATOR, anarana).trim();
    }

    List<EfitraFifidianana> getEfitraFifidiananaList()
    {
        List<EfitraFifidianana> efitraList = new ArrayList<>();
        for (Fokontany fokontany: fokontanyList)
        {
            efitraList.addAll(fokontany.getEfitraFifidiananaList());
        }
        return efitraList;

    }
}
