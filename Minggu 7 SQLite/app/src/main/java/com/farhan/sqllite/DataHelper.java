package com.farhan.sqllite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_sqllite";
    private static final String TABLE_NAME = "biodata";

    // Constructornya yang akan di eksekusi pada saat pembuatan objek (Intens)
    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Untuk membuat Create table pada database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = String.format("CREATE TABLE %s (no INTEGER PRIMARY KEY, nama TEXT, tgl DATE, jk TEXT, alamat TEXT)", TABLE_NAME);
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }
}