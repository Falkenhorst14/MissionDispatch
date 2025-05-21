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
    private int fuehrungsAusbildung;

    /**********************************************************************************************
     |                                      Konstruktoren                                          |
     **********************************************************************************************/

    public Einsatzkraft()
    {

    }

    public Einsatzkraft(String vorname, String nachname, String telefon, int tauchAusbildung,
                        int bootsAusbildung, int stroemungsretterAusbildung, int wrdAusbildung,
                        int sanAusbildung, int funkAusbildung, int fuehrungsAusbildung)
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

    public int getFuehrungsAusbildung() {
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

    public void setFuehrungsAusbildung(int fuehrungsAusbildung) {
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
        }

        return tauchausbildungString;
    }

}
