package com.example.khalessi.mitgliederdatenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alexandra Filbert on 24.01.18.
 */

public class MitgliederOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_MITGLIEDER = "mitglieder";
    public static final String COL_NAME_NAME = "name";
    public static final String COL_NAME_ANSCHRIFT = "anschrift";
    public static final String COL_NAME_ID = "_id";

    private final String SQL_COMMAND =
            "CREATE TABLE " + TABLE_NAME_MITGLIEDER + "(" +
                    COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_NAME_NAME + " VARCHAR(20) NOT NULL," +
                    COL_NAME_ANSCHRIFT + " VARCHAR(40) NOT NULL" +
                    " )";

    //
    public MitgliederOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_COMMAND);
        } catch (SQLException ex) {
            Log.e("SQLOpenHelper", "Fehler: " + ex.getMessage());
        }


    }

    public void insertRow(String name, String anschrift) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME_NAME, name);
        contentValues.put(COL_NAME_ANSCHRIFT, anschrift);


        long rowId = db.insert(TABLE_NAME_MITGLIEDER, null, contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: aaf. Upgrade auf neue Version
    }
}
