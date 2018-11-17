package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fivondronana extends IdValuePair {
    private List<Firaisana> firaisanaList;
    private Faritra parent;

    Fivondronana(String _string, Faritra _faritra) {
        super(_string);
        parent = _faritra;
        firaisanaList = retrieveKaomininaList(id);
    }

    public List<Firaisana> getFiraisanaList() {
        if (firaisanaList == null) {
            firaisanaList = retrieveKaomininaList(id);
        }
        return firaisanaList;
    }

    private List<Firaisana> retrieveKaomininaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.FIRAISANA_URL + id);

        return strs
                .parallelStream()
                .map(i -> new Firaisana(i, this))
                .collect(Collectors.toList());
    }

    String anaranaFeno() {
        return MessageFormat.format("{0}{1}{2}", parent.getAnarana(), ValimpifidiananaProcessor.SEPARATOR, anarana);
    }

    List<EfitraFifidianana> getEfitraFifidiananaList()
    {
        List<EfitraFifidianana> efitraList = new ArrayList<>();
        for (Firaisana firaisana : firaisanaList)
        {
            efitraList.addAll(firaisana.getEfitraFifidiananaList());
        }
        return efitraList;
    }
}
