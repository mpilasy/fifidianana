package org.njarasoa.fifidianana.model;

import org.njarasoa.fifidianana.Main;
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
                + Main.SEPARATOR
                + toeramPifidianana.anaranaFeno()
                + Main.SEPARATOR
                + anarana;
        str = str.trim().replace(" ", "_");
        return str;
    }


}
