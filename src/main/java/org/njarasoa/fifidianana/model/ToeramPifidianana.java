package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;
import org.njarasoa.fifidianana.util.RemoteHelper;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

class ToeramPifidianana extends IdValuePair {
    private final List<EfitraFifidianana> efitraFifidiananaList;
    private final Fokontany parent;

    ToeramPifidianana(String _string, Fokontany _fokontany) {
        super(_string);
        parent = _fokontany;
        efitraFifidiananaList = retrieveEfitraFifidiananaList(id);
    }

    List<EfitraFifidianana> getEfitraFifidiananaList() {
        return efitraFifidiananaList;
    }

    private List<EfitraFifidianana> retrieveEfitraFifidiananaList(String id) {
        List<String> strs = RemoteHelper.getURL(RemoteHelper.EFITRA_FIFIDIANANA_URL + id);

        return strs
                .parallelStream()
                .map(i -> new EfitraFifidianana(i, this))
                .collect(Collectors.toList());
    }

    String anaranaFeno() {
        return MessageFormat.format("{0}{1}{2}", parent.anaranaFeno(), ValimpifidiananaProcessor.SEPARATOR, anarana).trim();
    }
}
