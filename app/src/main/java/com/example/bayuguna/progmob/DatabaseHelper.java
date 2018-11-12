package com.example.bayuguna.progmob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "infoTi.db";
    public static final String TABLE_NAME = "users_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NIM";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "GMAIL";
    public static final String COL_5 = "TELP";
    public static final String COL_6 = "ALAMAT";
    public static final String COL_7 = "USERNAME";
    public static final String COL_8 = "PASSWORD";
    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NIM TEXT,NAME TEXT,GMAIL TEXT,TELP TEXT,ALAMAT TEXT,USERNAME TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nim, String nama, String gmail, String telp, String alamat, String username, String pass ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nim);
        contentValues.put(COL_3,nama);
        contentValues.put(COL_4,gmail);
        contentValues.put(COL_5,telp);
        contentValues.put(COL_6,alamat);
        contentValues.put(COL_7,username);
        contentValues.put(COL_8,pass);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public String searchPass(String username){
        db = this.getReadableDatabase();
        String query = "select name from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, pass = null;
        if (cursor.moveToFirst()){
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    pass = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return pass;
    }
}