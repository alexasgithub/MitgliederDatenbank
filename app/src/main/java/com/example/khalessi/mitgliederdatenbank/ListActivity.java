package com.example.khalessi.mitgliederdatenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    SQLiteDatabase mitgliederDatenbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        MitgliederOpenHelper moh = new MitgliederOpenHelper(this, "MitgliederDatenbank.db");
        mitgliederDatenbank = moh.getWritableDatabase();

        String[] projection = {MitgliederOpenHelper.COL_NAME_ID, MitgliederOpenHelper.COL_NAME_NAME, MitgliederOpenHelper.COL_NAME_ANSCHRIFT};
        Cursor cursor = mitgliederDatenbank.query(MitgliederOpenHelper.TABLE_NAME_MITGLIEDER, projection, "1=1", null, null, null, null);
   /*     cursor.moveToFirst();

        db_liste.setText("");

        while (!cursor.isAfterLast()) {
            String record = "" + cursor.getInt(0) + ","; //Id
            record += cursor.getString(1) + ","; //Name
            record += cursor.getString(2) + "\n"; //Anschrift

            db_liste.setText(db_liste.getText() + record);

            cursor.moveToNext();
        }*/
        //
        String[] anzeigespalten = new String[]{MitgliederOpenHelper.COL_NAME_NAME,
                MitgliederOpenHelper.COL_NAME_ANSCHRIFT};
        int[] anzeigeViews = new int[]{R.id.lv_name, R.id.lv_anschrift};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, anzeigespalten, anzeigeViews, 0);

        ListView db_liste = (ListView) findViewById(R.id.dbListe);
        db_liste.setAdapter(adapter);
        db_liste.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void onZurueckClick(View view) {
        finish();
    }
}
