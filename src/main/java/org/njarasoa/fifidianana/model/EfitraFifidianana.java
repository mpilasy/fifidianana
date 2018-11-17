package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;

import java.text.MessageFormat;
import java.util.Objects;

class EfitraFifidianana extends IdValuePair {
    private ToeramPifidianana parent;

    EfitraFifidianana(String _string, ToeramPifidianana _tp) {
        super(_string);
        parent = _tp;

        System.out.println(anaranaFeno());
    }

    private String anaranaFeno() {
        String str = MessageFormat.format("{0}{1}{2}", id, ValimpifidiananaProcessor.SEPARATOR, parent.anaranaFeno());

        if (!Objects.equals(anarana, parent.getAnarana())) {
            str = MessageFormat.format("{0}{1}{2}", str, ValimpifidiananaProcessor.SEPARATOR, anarana);
        }

        str = MessageFormat.format("{0}{1}{2}",
                ValimpifidiananaProcessor.getNextCount(),
                ValimpifidiananaProcessor.SEPARATOR,
                str.trim().replace(" ", "_"));
        return str;
    }


}
