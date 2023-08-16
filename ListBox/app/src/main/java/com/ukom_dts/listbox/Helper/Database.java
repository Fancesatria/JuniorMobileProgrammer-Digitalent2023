package com.ukom_dts.listbox.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_ukom_digitalent";
    public static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_MAHASISWA = "CREATE TABLE \"tblmahasiswa\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"nama\"\tTEXT,\n" +
            "\t\"kelamin\"\tTEXT,\n" +
            "\t\"asal\"\tTEXT,\n" +
            "\t\"alamat\"\tTEXT,\n" +
            "\t\"pend_terakhir\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ");";

    public static String CREATE_TABLE_USER = "CREATE TABLE \"tbluser\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"nama\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ");";
    public static String TABLE_MAHASISWA  = "tblmahasiswa";
    public static String TABLE_USER  = "tbluser";
    SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }


    public boolean runSQL(String sql){
        try {
            db.execSQL(sql);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public Cursor select(String sql){
        try{
            return db.rawQuery(sql, null);

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);
        db.execSQL(CREATE_TABLE_USER);
        Log.d("tbluser", "onCreate: "+CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLE_MAHASISWA +"'");
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLE_USER +"'");
        onCreate(db);
    }
}
