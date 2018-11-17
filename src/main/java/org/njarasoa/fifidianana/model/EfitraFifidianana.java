package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;

public class EfitraFifidianana extends IdValuePair {
    private ToeramPifidianana toeramPifidianana;

    public EfitraFifidianana(String _string, ToeramPifidianana _tp) {
        super(_string);
        toeramPifidianana = _tp;

        System.out.println(anaranaFeno());
    }

    public String anaranaFeno() {
        String str = id
                + ValimpifidiananaProcessor.SEPARATOR
                + toeramPifidianana.anaranaFeno();

        if (!anarana.equals(toeramPifidianana.getAnarana())) {
            str = str
                    + ValimpifidiananaProcessor.SEPARATOR
                    + anarana;
        }
        str = ValimpifidiananaProcessor.getNextCount()
                + ": "
                + str.trim().replace(" ", "_");
        return str;
    }


}
