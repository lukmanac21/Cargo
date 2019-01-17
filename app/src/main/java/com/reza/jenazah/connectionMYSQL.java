package com.reza.jenazah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by REZA on 31/12/2017.
 */

public class connectionMYSQL extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "crud.db";
    private static final int DATABASE_VERSION = 1;
    public connectionMYSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table cargo(no_resi varchar primary key, nama_jenazah text null, jk text null, umur int null, nama_pengirim text null, alamat text null, no_telp text null, pulau_tujuan text null, alamat_tujuan text null, tanggal_kirim date null, waktu_kirim int null, via text null, status text null, total_tarif int null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
