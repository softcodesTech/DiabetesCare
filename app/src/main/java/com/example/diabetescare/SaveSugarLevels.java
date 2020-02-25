package com.example.diabetescare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SaveSugarLevels extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Sugarlevels.db";
    public static final String TABLE_NAME = "Readings";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SUGAR";
    public SQLiteDatabase db;

    public SaveSugarLevels(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //  SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,SUGAR INTERGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void insertreadings(int EnterReadings) {
        ContentValues newValues = new ContentValues();
// Assign values for each row.
        newValues.put("FIRSTNAME", EnterReadings);

// Insert the row into your table
        db.insert("Readings", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
