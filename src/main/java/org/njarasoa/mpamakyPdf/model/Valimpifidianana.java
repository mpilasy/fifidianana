package org.njarasoa.mpamakyPdf.model;

public class Valimpifidianana {
    private String faritraId;
    private String faritra;
    private String fivondronanaId;
    private String fivondronana;
    private String firaisanaId;
    private String firaisana;
    private String fokontanyId;
    private String fokontany;
    private String biraoId;
    private String birao;
    private String efitraId;
    private String efitra;

    private long nisotratraAnarana;
    private long vatoMaty;

    private long[] valinyIsakyNyKandida;

    private String note;

    private Valimpifidianana(ValimpifidiananaBuilder _b) {
        faritraId = _b.faritraId;
        faritra = _b.faritra;
        fivondronanaId = _b.fivondronanaId;
        fivondronana = _b.fivondronana;
        firaisanaId = _b.firaisanaId;
        firaisana = _b.firaisana;
        fokontanyId = _b.fokontanyId;
        fokontany = _b.fokontany;
        biraoId = _b.biraoId;
        birao = _b.birao;
        efitraId = _b.efitraId;
        efitra = _b.efitra;
        nisotratraAnarana = _b.nisotratraAnarana;
        vatoMaty = _b.vatoMaty;
        valinyIsakyNyKandida = _b.valinyIsakyNyKandida;
        note = _b.note != null ? _b.note : "";
    }

    public String getFaritraId() {
        return faritraId;
    }

    public String getFaritra() {
        return faritra;
    }

    public String getFivondronanaId() {
        return fivondronanaId;
    }

    public String getFivondronana() {
        return fivondronana;
    }

    public String getFiraisanaId() {
        return firaisanaId;
    }

    public String getFiraisana() {
        return firaisana;
    }

    public String getFokontanyId() {
        return fokontanyId;
    }

    public String getFokontany() {
        return fokontany;
    }

    public String getBiraoId() {
        return biraoId;
    }

    public String getBirao() {
        return birao;
    }

    public String getEfitraId() {
        return efitraId;
    }

    public String getEfitra() {
        return efitra;
    }

    public long getNisotratraAnarana() {
        return nisotratraAnarana;
    }

    public long getVatoMaty() {
        return vatoMaty;
    }

    public long[] getValinyIsakyNyKandida() {
        return valinyIsakyNyKandida;
    }

    public String getNote() {
        return note;
    }

    public String toCsv() {
        String str = String.join(",", new String[]{faritraId.replace(",", "_"), faritra.replace(",", "_"),
                fivondronanaId.replace(",", "_"), fivondronana.replace(",", "_"),
                firaisanaId.replace(",", "_"), firaisana.replace(",", "_"),
                fokontanyId.replace(",", "_"), fokontany.replace(",", "_"),
                biraoId.replace(",", "_"), birao.replace(",", "_"),
                efitraId.replace(",", "_"), efitra.replace(",", "_"),
                String.valueOf(nisotratraAnarana),
                String.valueOf(vatoMaty)});
        str += ",";
        for (long vatoAzo : valinyIsakyNyKandida) {
            str += String.valueOf(vatoAzo) + ",";
        }
        str += note;
        return str;
    }

    public static class ValimpifidiananaBuilder {
        private String faritraId;
        private String faritra;
        private String fivondronanaId;
        private String fivondronana;
        private String firaisanaId;
        private String firaisana;
        private String fokontanyId;
        private String fokontany;
        private String biraoId;
        private String birao;
        private String efitraId;
        private String efitra;
        private long nisotratraAnarana;
        private long vatoMaty;
        private long[] valinyIsakyNyKandida = new long[36];
        private String note;

        public ValimpifidiananaBuilder withFaritra(String _str) {
            if (_str.contains("-")) {
                int pos = _str.indexOf("-");

                faritraId = _str.substring(0, pos).trim();
                faritra = _str.substring(pos + 1);
            } else {
                faritra = _str;
            }
            return this;
        }

        public ValimpifidiananaBuilder withFivondronana(String _str) {
            if (_str.contains("-")) {
                int pos = _str.indexOf("-");

                fivondronanaId = _str.substring(0, pos).trim();
                fivondronana = _str.substring(pos + 1);
            } else {
                fivondronana = _str;
            }
            return this;
        }

        public ValimpifidiananaBuilder withFiraisana(String _str) {
            if (_str.contains("-")) {
                int pos = _str.indexOf("-");

                firaisanaId = _str.substring(0, pos).trim();
                firaisana = _str.substring(pos + 1);
            } else {
                firaisana = _str;
            }
            return this;
        }

        public ValimpifidiananaBuilder withFokontany(String _str) {
            if (_str.contains("-")) {
                int pos = _str.indexOf("-");

                fokontanyId = _str.substring(0, pos).trim();
                fokontany = _str.substring(pos + 1);
            } else {
                fokontany = _str;
            }
            return this;
        }

        public ValimpifidiananaBuilder withBirao(String _str) {
            if (_str.contains("-")) {
                int pos = _str.indexOf("-");

                biraoId = _str.substring(0, pos).trim();
                birao = _str.substring(pos + 1);
            } else {
                birao = _str;
            }
            return this;
        }

        public ValimpifidiananaBuilder withEfitra(String _str) {
            if (_str.contains("-")) {
                int pos = _str.indexOf("-");

                efitraId = _str.substring(0, pos).trim();
                efitra = _str.substring(pos + 1);
            } else {
                efitra = _str;
            }
            return this;
        }

        public ValimpifidiananaBuilder withVatoAzo(int _id, long _vatoAzo) {
            valinyIsakyNyKandida[_id - 1] = _vatoAzo;
            return this;
        }

        public ValimpifidiananaBuilder withVatoMaty(long l) {
            vatoMaty = l;
            return this;
        }

        public ValimpifidiananaBuilder withNisotratraAnarana(long l) {
            nisotratraAnarana = l;
            return this;
        }

        public ValimpifidiananaBuilder withNote(String _str) {
            note = _str;
            return this;
        }

        public Valimpifidianana build() {
            return new Valimpifidianana(this);
        }
    }

}
