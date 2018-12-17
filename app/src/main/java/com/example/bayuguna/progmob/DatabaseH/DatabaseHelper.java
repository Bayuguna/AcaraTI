package com.example.bayuguna.progmob.DatabaseH;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "infoTi.db";
    public static final String TABLE_NAME = "users_table";
    public static final String TABLE_NAME_2 = "kepanitiaan_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NIM";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "GMAIL";
    public static final String COL_5 = "TELP";
    public static final String COL_6 = "ALAMAT";
    public static final String COL_7 = "USERNAME";
    public static final String COL_8 = "PASSWORD";

    public static final String TABLE_NAME_3 = "kegiatan_table";
    public static final String COL_ID_KEGIATAN = "ID";
    public static final String COL_PIC_KEGIATAN = "PIC";
    public static final String COL_NAMA_KEGIATAN = "NAMA";
    public static final String COL_TANGGAL_KEGIATAN = "TANGGAL";
    public static final String COL_DESKRIPSI_KEGIATAN = "DESKRIPSI";
    public static final String COL_STATUS_KEGIATAN = "STATUS";

    public static final String TABLE_NAME_4 = "det_kegiatan_table";
    public static final String COL_ID_DET_KEGIATAN = "ID";
    public static final String COL_ID_KEGIATANS = "ID_KEGIATAN";
    public static final String COL_SIE_DET_KEGIATAN = "SIE";
    public static final String COL_JOB_DESC_DET_KEGIATAN = "JOB_DESC";
    public static final String COL_KUOTA_DET_KEGIATAN = "KUOTA";
    public static final String COL_NAMA_KOOR_DET_KEGIATAN = "NAMA_KOOR";
    public static final String COL_LINE_ID_DET_KEGIATAN = "LINE_ID";
    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NIM TEXT,NAME TEXT,GMAIL TEXT,TELP TEXT,ALAMAT TEXT,USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("create table " + TABLE_NAME_2+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ID_USERS INTEGER,ID_DET_KEGIATAN INTEGER,ALASAN TEXT,TANGGAL_DAFTAR TEXT,STATUS_KEPANITIAAN TEXT)");
        db.execSQL("create table " + TABLE_NAME_3+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PIC TEXT,NAMA TEXT,TANGGAL TEXT,DESKRIPSI TEXT, STATUS TEXT)");
        db.execSQL("create table " + TABLE_NAME_4+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ID_KEGIATAN INTEGER,SIE TEXT,JOB_DESC TEXT,KUOTA TEXT, NAMA_KOOR TEXT, LINE_ID TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_4);
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

    public boolean insertKegiatan(String pic, String nama, String tanggal, String deskripsi, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PIC_KEGIATAN,pic);
        contentValues.put(COL_NAMA_KEGIATAN,nama);
        contentValues.put(COL_TANGGAL_KEGIATAN,tanggal);
        contentValues.put(COL_DESKRIPSI_KEGIATAN,deskripsi);
        contentValues.put(COL_STATUS_KEGIATAN,status);
        long result = db.insert(TABLE_NAME_3,null ,contentValues);
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

    public Cursor getUsers(){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }

    public Cursor getKegiatanSQL(){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_3,null);
        return  res;
    }

    public void deleteKegiatan(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
    }

    //Detail Kegiatan
    public boolean insertDetKegiatan(int id_kegiatan, String sie, String job_desc, String kuota, String nama_koor, String line_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_KEGIATANS,id_kegiatan);
        contentValues.put(COL_SIE_DET_KEGIATAN,sie);
        contentValues.put(COL_JOB_DESC_DET_KEGIATAN,job_desc);
        contentValues.put(COL_KUOTA_DET_KEGIATAN,kuota);
        contentValues.put(COL_NAMA_KOOR_DET_KEGIATAN,nama_koor);
        contentValues.put(COL_LINE_ID_DET_KEGIATAN,line_id);
        long result = db.insert(TABLE_NAME_4,null ,contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getDetKegiatanSQL(int id){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_4+" where "+COL_ID_KEGIATANS+" = " +id,null);

        int count = res.getCount();

        if (count>0){
            do {

            }while (res.moveToNext());
        }
        return  res;
    }

    public void deleteDetKegiatan(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
    }

    public boolean insertRiwayat(int id_kegiatan, String sie, String job_desc, String kuota, String nama_koor, String line_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_KEGIATANS,id_kegiatan);
        contentValues.put(COL_SIE_DET_KEGIATAN,sie);
        contentValues.put(COL_JOB_DESC_DET_KEGIATAN,job_desc);
        contentValues.put(COL_KUOTA_DET_KEGIATAN,kuota);
        contentValues.put(COL_NAMA_KOOR_DET_KEGIATAN,nama_koor);
        contentValues.put(COL_LINE_ID_DET_KEGIATAN,line_id);
        long result = db.insert(TABLE_NAME_4,null ,contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }


}