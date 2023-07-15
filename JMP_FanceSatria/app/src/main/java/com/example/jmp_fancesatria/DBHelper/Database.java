package com.example.jmp_fancesatria.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_jmp";
    public static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_BARANG = "CREATE TABLE \"tblbarang\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"nama\"\tTEXT,\n" +
            "\t\"stok\"\tINTEGER,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ");";
    public static String TABLE_BARANG  = "tblbarang";
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

    public void buatTable(){
        runSQL(CREATE_TABLE_BARANG);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BARANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLE_BARANG +"'");
        onCreate(db);
    }
}
