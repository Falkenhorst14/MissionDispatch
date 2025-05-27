package com.example.missiondispatch;

public class Abschnitt {


    private int id;
    private String name;

    /**********************************************************************************************
     |                                      Konstruktoren                                          |
     **********************************************************************************************/

    public Abschnitt () {}

    public Abschnitt (String name)
    {
        this.name = name;
    }

    public Abschnitt (int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**********************************************************************************************
     |                                          Getter                                             |
     **********************************************************************************************/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**********************************************************************************************
     |                                          Setter                                             |
     **********************************************************************************************/

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
