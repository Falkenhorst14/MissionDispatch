package com.example.missiondispatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//	Kategorien nach Nummern:
//	1. General
//	2. Life in the home, friends and relationships
//	3. Local area, facilities and getting around
//	4. Sports, outdoor pursuits and healthy lifestyle
//	5. Food and drink as aspects of culture and health
//	6. Socialising, special occasions and festivals
//	7. TV, Films and music
//	8. Holidays and exchanges
//	9. Environmental, cultural and social issues
//	10. School life in the UK and Germany
//  11. Work Experience, future jobs and jobs, working abroad
public class DBHandler extends SQLiteOpenHelper
{

    //T
    private static final String DB_Name = "MissionDispatchDB";
    private static final int DB_Version = 3;
    private static final String Table_FIRST = "Einsatzkraefte";
    private static final String col_ID = "id";
    private static final String col_VORNAME = "vorname";
    private static final String col_NACHNAME = "nachname";

    private static final String col_TELEFON = "telefon";
    private static final String col_GEBURTSDATUM = "geburtsdatum";
    private static final String col_IM_EINSATZ = "imEinsatz";
    private static final String col_TAUCHAUSBILDUNG = "tauchausbildung";
    private static final String col_BOOTSAUSBILDUNG = "bootsausbildung";
    private static final String col_STROEMUNGSRETTUNGSAUSBILDUNG = "stroemungsrettungsausbildung";
    private static final String col_WRDAUSBILDUNG = "wrdausbildung";
    private static final String col_SANASUBILDUNG = "sanausbildung";
    private static final String col_FUNKAUSBILDUNG = "funkausbildung";
    private static final String col_FUEHRUNGSAUSBILDUNG = "fuehrungsausbildung";


    private static final String Table_SECOND = "Abschnitte";
    private static final String col_ID_AS = "id_abschnitte";
    private static final String col_NAME_AS = "name_abschnitte";


    public DBHandler (Context context)
    {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + Table_FIRST + " ("
                + col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_VORNAME + " TEXT,"
                + col_NACHNAME + " TEXT,"
                + col_TELEFON + " TEXT,"
                + col_GEBURTSDATUM + " TEXT,"
                + col_IM_EINSATZ + " INTEGER,"
                + col_TAUCHAUSBILDUNG + " INTEGER,"
                + col_BOOTSAUSBILDUNG + " INTEGER,"
                + col_STROEMUNGSRETTUNGSAUSBILDUNG + " INTEGER,"
                + col_WRDAUSBILDUNG + " INTEGER,"
                + col_SANASUBILDUNG + " INTEGER,"
                + col_FUNKAUSBILDUNG + " INTEGER,"
                + col_FUEHRUNGSAUSBILDUNG + " TEXT);";

        db.execSQL(query);

        String query2 = "CREATE TABLE IF NOT EXISTS " + Table_SECOND + " ("
                + col_ID_AS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_NAME_AS + " TEXT);";

        db.execSQL(query2);

    }

    /**********************************************************************************************
     |                                   GETTER Einsatzkraft                                       |
     **********************************************************************************************/

    public Einsatzkraft getEinsatzkraft(int id)
    {
        Einsatzkraft einsatzkraft = new Einsatzkraft();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + Table_FIRST + " WHERE " + col_ID + " == " + id + " LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            einsatzkraft.setId(cursor.getInt(0));
            einsatzkraft.setVorname(cursor.getString(1));
            einsatzkraft.setNachname(cursor.getString(2));
            einsatzkraft.setTelefon(cursor.getString(3));
            einsatzkraft.setGeburtsdatum(cursor.getString(4));
            einsatzkraft.setImEinsatz(cursor.getInt(5) == 1);
            einsatzkraft.setTauchAusbildung(cursor.getInt(6));
            einsatzkraft.setBootsAusbildung(cursor.getInt(7));
            einsatzkraft.setStroemungsrettungsAusbildung(cursor.getInt(8));
            einsatzkraft.setWrdAusbildung(cursor.getInt(9));
            einsatzkraft.setSanAusbildung(cursor.getInt(10));
            einsatzkraft.setFunkAusbildung(cursor.getInt(11));
            einsatzkraft.setFuehrungsAusbildung(cursor.getString(12));

            cursor.close();
        }
        return einsatzkraft;
    }


    public List<Einsatzkraft> getAllEinsatzkraefte()
    {
        List<Einsatzkraft> einsatzkraefteList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + Table_FIRST;

        Cursor cursor = db.rawQuery(query, null);

        //Schleife iteriert durch alle Zeilen, waehrend die Daten in List geladen werden
        if(cursor.moveToFirst())
        {
            do {
                Einsatzkraft einsatzkraft = new Einsatzkraft();
                einsatzkraft.setId(cursor.getInt(0));
                einsatzkraft.setVorname(cursor.getString(1));
                einsatzkraft.setNachname(cursor.getString(2));
                einsatzkraft.setTelefon(cursor.getString(3));
                einsatzkraft.setGeburtsdatum(cursor.getString(4));
                einsatzkraft.setImEinsatz(cursor.getInt(5) == 1);
                einsatzkraft.setTauchAusbildung(cursor.getInt(6));
                einsatzkraft.setBootsAusbildung(cursor.getInt(7));
                einsatzkraft.setStroemungsrettungsAusbildung(cursor.getInt(8));
                einsatzkraft.setWrdAusbildung(cursor.getInt(9));
                einsatzkraft.setSanAusbildung(cursor.getInt(10));
                einsatzkraft.setFunkAusbildung(cursor.getInt(11));
                einsatzkraft.setFuehrungsAusbildung(cursor.getString(12));
                einsatzkraefteList.add(einsatzkraft);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return einsatzkraefteList;
    }


    /**********************************************************************************************
     |                                   UPDATE Einsatzkraft                                       |
     **********************************************************************************************/
    //Setzt den Boolean "imEinsatz". --> Muss im uebergebenen Objekt vorher programmatisch angepasst werden
    public void updateEinsatzkraftStatus(Einsatzkraft einsatzkraft) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("imEinsatz", einsatzkraft.getImEinsatz());

        db.update(Table_FIRST, data, "ID=" + einsatzkraft.getId(), null);
        db.close();
    }

    /*//Spaeterer Uebergabe-Param wird String today sein (herausgenommen)
    public Word getNext()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Word naechstesWort = new Word();

        //Datum Umwandlung
        LocalDate today = LocalDate.now();
        String todayString = today.toString();
        todayString = "'" + todayString + "'";
        //Test Datum
//        String todayString = "'2024-08-05'";

        String query = "SELECT * FROM " + Table_FIRST + " WHERE " + col_NAECHSTEWIEDERHOLLUNG + " <= " + todayString + " LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);

//        String ergebnisse = DatabaseUtils.dumpCursorToString(cursor);
//        System.out.println(ergebnisse);

        //Schleife iteriert durch alle Zeilen, waehrend die Daten in List geladen werden
        if(cursor.moveToFirst())
        {
            do {
                naechstesWort.setId(cursor.getInt(0));
                naechstesWort.setWort(cursor.getString(1));
                naechstesWort.setTranslation(cursor.getString(2));
                naechstesWort.setKategorie(cursor.getInt(3));
                naechstesWort.setNaechsteWiederholung(cursor.getString(4));
                naechstesWort.setLernStatus(cursor.getInt(5));
                naechstesWort.setGewusst(cursor.getInt(6));
                naechstesWort.setLetzteWiederholung(cursor.getString(7));
            }
            while(cursor.moveToNext());
        }
//        System.out.println("Hallo");

        cursor.close();
        return naechstesWort;
    }*/


    public Abschnitt getAbschnitt(int id)
    {
        Abschnitt abschnitt = new Abschnitt();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + Table_SECOND + " WHERE " + col_ID_AS + " == " + id + " LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            abschnitt.setId(cursor.getInt(0));
            abschnitt.setName(cursor.getString(1));

            cursor.close();
        }
        return abschnitt;
    }


    public List<Abschnitt> getAllAbschnitte()
    {
        List<Abschnitt> abschnitteList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + Table_FIRST;

        Cursor cursor = db.rawQuery(query, null);

        //Schleife iteriert durch alle Zeilen, waehrend die Daten in List geladen werden
        if(cursor.moveToFirst())
        {
            do {
                Abschnitt abschnitt = new Abschnitt();
                abschnitt.setId(cursor.getInt(0));
                abschnitt.setName(cursor.getString(1));
                abschnitteList.add(abschnitt);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return abschnitteList;
    }



    //Funktion, die jedes Wort zu dem angegebenen Lernstatus anzeigt, was ein Datum enthält.
   /* public ArrayList<Word> getWordsByLernStatus(int lernStatus) {
        ArrayList<Word> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + Table_FIRST + " WHERE " + col_LERNSTATUS + " = ? AND " + col_NAECHSTEWIEDERHOLLUNG + " IS NOT NULL";
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(lernStatus) });

        if (cursor.moveToFirst()) {
            do {
                Word wort = new Word();
                wort.setId(cursor.getInt(cursor.getColumnIndexOrThrow(col_ID)));
                wort.setWort(cursor.getString(cursor.getColumnIndexOrThrow(col_WORT)));
                wort.setTranslation(cursor.getString(cursor.getColumnIndexOrThrow(col_TRANSLATION)));
                wort.setKategorie(cursor.getInt(cursor.getColumnIndexOrThrow(col_KATEGORIE)));
                wort.setNaechsteWiederholung(cursor.getString(cursor.getColumnIndexOrThrow(col_NAECHSTEWIEDERHOLLUNG)));
                wort.setLernStatus(cursor.getInt(cursor.getColumnIndexOrThrow(col_LERNSTATUS)));
                wort.setGewusst(cursor.getInt(cursor.getColumnIndexOrThrow(col_GEWUSST)));
                arrayList.add(wort);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }*/


    /*public int[] getFinishedStats()
    {
        int[] stats = new int[2];
        LocalDate today = LocalDate.now();
        String todayString = today.toString();
        todayString = "'" + todayString + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        String queryFinished = "SELECT COUNT (*) FROM " + Table_FIRST + " WHERE " + col_NAECHSTEWIEDERHOLLUNG + " <= " + todayString;

        Cursor cursorFinished = db.rawQuery(queryFinished, null);

        if (cursorFinished != null && cursorFinished.moveToFirst()) {
            stats[0] = cursorFinished.getInt(0);
            cursorFinished.close();
        }

        String queryTotal = "SELECT COUNT (*) FROM " + Table_FIRST + " WHERE " + col_NAECHSTEWIEDERHOLLUNG
                + " > " + todayString + " OR " + col_NAECHSTEWIEDERHOLLUNG + " IS NULL";

        Cursor cursorTotal = db.rawQuery(queryTotal, null);

        if (cursorTotal != null && cursorTotal.moveToFirst()) {
            stats[1] = cursorTotal.getInt(0);
            cursorTotal.close();
        }
        return stats;
    }*/



    /*public int[] getNewWordsByCategory(int kategorie)
    {
        int[] stats = new int[2];
        LocalDate today = LocalDate.now();
        String todayString = today.toString();
        todayString = "'" + todayString + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        String queryFinished = "SELECT COUNT (*) FROM " + Table_FIRST + " WHERE " + col_NAECHSTEWIEDERHOLLUNG + " IS NULL AND " + col_KATEGORIE + " == " + kategorie;

        Cursor cursorFinished = db.rawQuery(queryFinished, null);

        if (cursorFinished != null && cursorFinished.moveToFirst()) {
            stats[0] = cursorFinished.getInt(0);
            cursorFinished.close();
        }
        return stats;
    }*/

    /*//Alle Wörter, die min. einmal angefasst wurden
    public int getAllLearnedWords()
    {
        int stats = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        String queryFinished = "SELECT COUNT (*) FROM " + Table_FIRST + " WHERE " + col_NAECHSTEWIEDERHOLLUNG + " IS NOT NULL";

        Cursor cursorFinished = db.rawQuery(queryFinished, null);

        if (cursorFinished != null && cursorFinished.moveToFirst()) {
            stats = cursorFinished.getInt(0);
            cursorFinished.close();
        }
        return stats;
    }*/


    // Bekomme Angaben zum Lernstatus des zu updatenden Wortes
    /*public void updateWord(Context context, Word geaendertesWort)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("lernstatus", geaendertesWort.getLernStatus());
        data.put("naechsteWiederholung", geaendertesWort.getNaechsteWiederholung());
        data.put("letzteWiederholung", geaendertesWort.getLetzteWiederholung());

        sqLiteDatabase.update(Table_FIRST, data, "ID=" + geaendertesWort.getId(), null);
        sqLiteDatabase.close();
    }*/




    /* TODO Aendern: ID Parameter hardcoded*/

   /* public void updateUserName(String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put( "name", name);

        sqLiteDatabase.update(Table_SECOND, data, "ID= 1", null);
        sqLiteDatabase.close();
    }*/

}