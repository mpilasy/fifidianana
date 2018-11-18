package org.njarasoa.fifidianana.rafitra.model;

import org.njarasoa.fifidianana.rafitra.LisitraBiraoProcessor;
import org.njarasoa.fifidianana.util.IdValuePair;

import java.text.MessageFormat;
import java.util.Objects;

class EfitraFifidianana extends IdValuePair {
    private final ToeramPifidianana parent;

    EfitraFifidianana(String _string, ToeramPifidianana _tp) {
        super(_string);
        parent = _tp;

        System.out.println(anaranaFeno());
    }

    private String anaranaFeno() {
        String str = MessageFormat.format("{0}{1}{2}", id, LisitraBiraoProcessor.SEPARATOR, parent.anaranaFeno());

        if (!Objects.equals(anarana, parent.getAnarana())) {
            str = MessageFormat.format("{0}{1}{2}", str, LisitraBiraoProcessor.SEPARATOR, anarana);
        }

        str = MessageFormat.format("{0}{1}{2}",
                LisitraBiraoProcessor.getNextCount(),
                LisitraBiraoProcessor.SEPARATOR,
                str.trim().replace(" ", "_"));
        return str;
    }


}
