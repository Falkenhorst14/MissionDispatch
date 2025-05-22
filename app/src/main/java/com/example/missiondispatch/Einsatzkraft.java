package com.example.missiondispatch;

public class Einsatzkraft {

    private int id;
    private String vorname;
    private String nachname;
    private String telefon;

    private boolean imEinsatz;

    //Qualifikationen
    private int tauchAusbildung;
    private int bootsAusbildung;
    private int stroemungsretterAusbildung;
    private int wrdAusbildung;
    private int sanAusbildung;
    private int funkAusbildung;
    private String fuehrungsAusbildung;

    /**********************************************************************************************
     |                                      Konstruktoren                                          |
     **********************************************************************************************/

    public Einsatzkraft()
    {

    }

    public Einsatzkraft(String vorname, String nachname, String telefon, int tauchAusbildung,
                        int bootsAusbildung, int stroemungsretterAusbildung, int wrdAusbildung,
                        int sanAusbildung, int funkAusbildung, String fuehrungsAusbildung)
    {
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = telefon;
        this.tauchAusbildung = tauchAusbildung;
        this.bootsAusbildung = bootsAusbildung;
        this.stroemungsretterAusbildung = stroemungsretterAusbildung;
        this.wrdAusbildung = wrdAusbildung;
        this.sanAusbildung = sanAusbildung;
        this.funkAusbildung = funkAusbildung;
        this.fuehrungsAusbildung = fuehrungsAusbildung;
    }

    //experimental
    public Einsatzkraft(int id, String vorname, String nachname, String telefon, int tauchAusbildung,
                        int bootsAusbildung, int stroemungsretterAusbildung, int wrdAusbildung,
                        int sanAusbildung, int funkAusbildung, String fuehrungsAusbildung)
    {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = telefon;
        this.tauchAusbildung = tauchAusbildung;
        this.bootsAusbildung = bootsAusbildung;
        this.stroemungsretterAusbildung = stroemungsretterAusbildung;
        this.wrdAusbildung = wrdAusbildung;
        this.sanAusbildung = sanAusbildung;
        this.funkAusbildung = funkAusbildung;
        this.fuehrungsAusbildung = fuehrungsAusbildung;
    }


    /**********************************************************************************************
     |                                          Getter                                             |
     **********************************************************************************************/

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getTelefon() {
        return telefon;
    }

    public boolean getImEinsatz() {
        return imEinsatz;
    }

    public int getTauchAusbildung() {
        return tauchAusbildung;
    }

    public int getBootsAusbildung() {
        return bootsAusbildung;
    }

    public int getStroemungsrettungsAusbildung() {
        return stroemungsretterAusbildung;
    }

    public int getWrdAusbildung() {
        return wrdAusbildung;
    }

    public int getSanAusbildung() {
        return sanAusbildung;
    }

    public int getFunkAusbildung() {
        return funkAusbildung;
    }

    public String getFuehrungsAusbildung() {
        return fuehrungsAusbildung;
    }


    /**********************************************************************************************
     |                                          Setter                                             |
     **********************************************************************************************/


    public void setId(int id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setImEinsatz(boolean imEinsatz) {
        this.imEinsatz = imEinsatz;
    }

    public void setTauchAusbildung(int tauchAusbildung) {
        this.tauchAusbildung = tauchAusbildung;
    }

    public void setBootsAusbildung(int bootsAusbildung) {
        this.bootsAusbildung = bootsAusbildung;
    }

    public void setStroemungsrettungsAusbildung(int stroemungsretterAusbildung) {
        this.stroemungsretterAusbildung = stroemungsretterAusbildung;
    }

    public void setWrdAusbildung(int wrdAusbildung) {
        this.wrdAusbildung = wrdAusbildung;
    }

    public void setSanAusbildung(int sanAusbildung) {
        this.sanAusbildung = sanAusbildung;
    }

    public void setFunkAusbildung(int funkAusbildung) {
        this.funkAusbildung = funkAusbildung;
    }

    public void setFuehrungsAusbildung(String fuehrungsAusbildung) {
        this.fuehrungsAusbildung = fuehrungsAusbildung;
    }


    /**********************************************************************************************
     |                                 zusätzliche Funktionen                                     |
     **********************************************************************************************/

    public String getTauchausbildungString (int tauchAusbildung) {
        String tauchausbildungString = "";

        if (tauchAusbildung == 0) {
            tauchausbildungString = "Keine";
        } else if (tauchAusbildung == 1) {
            tauchausbildungString = "Signalmann";
        } else if (tauchAusbildung == 2) {
            tauchausbildungString = "Einsatztaucher 1";
        } else if (tauchAusbildung == 3) {
            tauchausbildungString = "Einsatztaucher 2";
        } else if (tauchAusbildung == 4) {
            tauchausbildungString = "Taucheinsatzführer";
        } else {
            tauchausbildungString = "Fehler";
        }

        return tauchausbildungString;
    }

    public String getFuehrungsausbildungString (String fuehrungsausbildung) {
        String fuehrungsausbildungString = "";

        if (fuehrungsausbildung == "") {
            fuehrungsausbildungString = "Keine";
        } else if (fuehrungsausbildung == "●") {
            fuehrungsausbildungString = "Truppführer";
        } else if (fuehrungsausbildung == "● ●") {
            fuehrungsausbildungString = "Gruppenführer";
        } else if (fuehrungsausbildung == "● ● ●") {
            fuehrungsausbildungString = "Zugführer";
        } else if (fuehrungsausbildung == "❚") {
            fuehrungsausbildungString = "Verbandsführer";
        } else {
            fuehrungsausbildungString = "Fehler";
        }

        return fuehrungsausbildungString;
    }

    public String getBootsausbildungString (int bootsausbildung) {
        String bootsausbildungString = "";

        if (bootsausbildung == 0) {
            bootsausbildungString = "Keine";
        } else if (bootsausbildung == 1) {
            bootsausbildungString = "Bootsführer A";
        } else if (bootsausbildung == 2) {
            bootsausbildungString = "Bootsführer B";
        } else if (bootsausbildung == 12) {
            bootsausbildungString = "Bootsführer A/B";
        } else {
            bootsausbildungString = "Fehler";
        }

        return bootsausbildungString;
    }

    public String getStroemungsrettungsausbildungString (int stroemungsrettungsausbildung) {
        String stroemungsrettungsausbildungString = "";

        if (stroemungsrettungsausbildung == 0) {
            stroemungsrettungsausbildungString = "Keine";
        } else if (stroemungsrettungsausbildung == 1) {
            stroemungsrettungsausbildungString = "Strömungsretter 1";
        } else if (stroemungsrettungsausbildung == 2) {
            stroemungsrettungsausbildungString = "Strömungsretter 2";
        } else if (stroemungsrettungsausbildung == 3) {
            stroemungsrettungsausbildungString = "Gruppenführer SR";
        } else {
            stroemungsrettungsausbildungString = "Fehler";
        }

        return stroemungsrettungsausbildungString;
    }


    public String getWrdausbildungString (int wrdausbildung) {
        String wrdausbildungString = "";

        if (wrdausbildung == 0) {
            wrdausbildungString = "Keine";
        } else if (wrdausbildung == 1) {
            wrdausbildungString = "Basisausbildung";
        } else if (wrdausbildung == 2) {
            wrdausbildungString = "Fachausbildung WRD";
        } else if (wrdausbildung == 3) {
            wrdausbildungString = "Wachführer";
        } else {
            wrdausbildungString = "Fehler";
        }

        return wrdausbildungString;
    }

    public String getSanausbildungString (int sanausbildung) {
        String sanausbildungString = "";

        if (sanausbildung == 0) {
            sanausbildungString = "Keine";
        } else if (sanausbildung == 1) {
            sanausbildungString = "Ersthelfer";
        } else if (sanausbildung == 2) {
            sanausbildungString = "Sanitätshelfer";
        } else if (sanausbildung == 3) {
            sanausbildungString = "Sanitäter";
        } else if (sanausbildung == 4 ){
            sanausbildungString = "RD-Qualifikation";
        } else {
            sanausbildungString = "Fehler";
        }
        return sanausbildungString;
    }

    public String getFunkausbildungString (int funkausbildung) {
        String funkausbildungString = "";

        if (funkausbildung == 0) {
            funkausbildungString = "Keine";
        } else if (funkausbildung == 1) {
            funkausbildungString = "Funkunterweisung";
        } else if (funkausbildung == 2) {
            funkausbildungString = "Sprechfunker";
        } else if (funkausbildung == 3) {
            funkausbildungString = "BOS-Sprechfunker";
        } else {
            funkausbildungString = "Fehler";
        }
        return funkausbildungString;
    }

}
