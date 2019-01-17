package com.reza.jenazah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by REZA on 11/01/2018.
 */

public class connectionLogin extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "crud2.db";
    private static final int DATABASE_VERSION = 1;
    public connectionLogin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table login(username varchar primary key, password varchar null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

