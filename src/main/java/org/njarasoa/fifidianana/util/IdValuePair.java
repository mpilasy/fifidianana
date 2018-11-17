package org.njarasoa.fifidianana.util;

public class IdValuePair {
    protected String id;
    protected String anarana;

    public IdValuePair(String _id, String _anarana) {
        id = _id.trim();
        anarana = _anarana.trim();
    }

    protected IdValuePair(String _string) {
        String str = _string.trim();
        int pos = str.indexOf("-");

        id = str.substring(0, pos).trim();
        anarana = str.substring(pos + 1).trim();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnarana() {
        return anarana;
    }

    public void setAnarana(String anarana) {
        this.anarana = anarana;
    }
}
