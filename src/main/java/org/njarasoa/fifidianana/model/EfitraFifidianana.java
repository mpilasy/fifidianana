package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;

import java.text.MessageFormat;
import java.util.Objects;

public class EfitraFifidianana extends IdValuePair {
    private ToeramPifidianana toeramPifidianana;

    public EfitraFifidianana(String _string, ToeramPifidianana _tp) {
        super(_string);
        toeramPifidianana = _tp;

        System.out.println(anaranaFeno());
    }

    public String anaranaFeno() {
        String str = MessageFormat.format("{0}{1}{2}", id, ValimpifidiananaProcessor.SEPARATOR, toeramPifidianana.anaranaFeno());

        if (!Objects.equals(anarana, toeramPifidianana.getAnarana())) {
            str = MessageFormat.format("{0}{1}{2}", str, ValimpifidiananaProcessor.SEPARATOR, anarana);
        }

        str = MessageFormat.format("{0}{1}{2}", ValimpifidiananaProcessor.SEPARATOR,
                ValimpifidiananaProcessor.getNextCount(),
                str.trim().replace(" ", "_"));
        return str;
    }


}
