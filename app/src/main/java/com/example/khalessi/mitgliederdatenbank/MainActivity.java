package com.example.khalessi.mitgliederdatenbank;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mitgliederDatenbank;
    private EditText et_name, et_anschrift;
    private Button btn_aufnehmen;
    private ListView dbListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Zugriff auf die Edit-Text-Felder schaffen
        et_name = (EditText) findViewById(R.id.et_name);
        et_anschrift = (EditText) findViewById(R.id.et_anschrift);
        dbListe = (ListView) findViewById(R.id.dbListe);

        MitgliederOpenHelper moh = new MitgliederOpenHelper(this, "MitgliederDatenbank.db");
        mitgliederDatenbank = moh.getWritableDatabase();



    }
    public void aufnehmenClick (View view) {



        ContentValues neuesMitglied = new ContentValues();
        neuesMitglied.put(MitgliederOpenHelper.COL_NAME_NAME, et_name.getText().toString());
        neuesMitglied.put(MitgliederOpenHelper.COL_NAME_ANSCHRIFT, et_anschrift.getText().toString());


        long rowId = mitgliederDatenbank.insert(MitgliederOpenHelper.TABLE_NAME_MITGLIEDER, null, neuesMitglied);
    }

    public void auflistenClick(View view) {

        String [] projection = {MitgliederOpenHelper.COL_NAME_ID, MitgliederOpenHelper.COL_NAME_NAME, MitgliederOpenHelper.COL_NAME_ANSCHRIFT};
        Cursor cursor = mitgliederDatenbank.query(MitgliederOpenHelper.TABLE_NAME_MITGLIEDER, projection, "*",null, null, null, null);
        cursor.moveToFirst();

        dbListe.setText("");
        while (!cursor.isAfterLast()) {
            String record = ""+cursor.getInt(0)+","; //Id
            record+=cursor.getString(1)+","; //Name
            record+=cursor.getString(1)+"\n"; //Anschrift

            dbListe.setText(dbListe.getText()+record);

            cursor.moveToNext();
        }


    }
}
