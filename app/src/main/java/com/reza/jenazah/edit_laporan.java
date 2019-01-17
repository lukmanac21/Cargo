package com.reza.jenazah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by REZA on 10/01/2018.
 */

public class edit_laporan  extends AppCompatActivity {

    protected Cursor cursor;
    connectionMYSQL dbHelper;
    TextView noresi, namajenazah, umur, jk, namapengirim, alamatpengirim, notelp, pulau, alamattujuan, tanggal, waktu, via, status,total;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_laporan);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new connectionMYSQL(this);
        noresi = (TextView) findViewById(R.id.noresi1);
        namajenazah = (TextView) findViewById(R.id.namajenazah1);
        umur = (TextView) findViewById(R.id.umur1);
        jk = (TextView) findViewById(R.id.jk1);
        namapengirim = (TextView) findViewById(R.id.namapengirim1);
        alamatpengirim = (TextView) findViewById(R.id.alamatp1);
        notelp = (TextView) findViewById(R.id.notelp1);
        pulau = (TextView) findViewById(R.id.pulau1);
        alamattujuan = (TextView) findViewById(R.id.alamatt1);
        tanggal = (TextView) findViewById(R.id.tanggal1);
        waktu = (TextView) findViewById(R.id.waktu1);
        via = (TextView) findViewById(R.id.via2);
        status = (TextView) findViewById(R.id.status1);
        total = (TextView) findViewById(R.id.total_bayar1);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM cargo WHERE no_resi = '" +
                getIntent().getStringExtra("no resi") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            noresi.setText(cursor.getString(0).toString());
            namajenazah.setText(cursor.getString(1).toString());
            umur.setText(cursor.getString(2).toString());
            jk.setText(cursor.getString(3).toString());
            namapengirim.setText(cursor.getString(4).toString());
            alamatpengirim.setText(cursor.getString(5).toString());
            notelp.setText(cursor.getString(6).toString());
            pulau.setText(cursor.getString(7).toString());
            alamattujuan.setText(cursor.getString(8).toString());
            tanggal.setText(cursor.getString(9).toString());
            waktu.setText(cursor.getString(10).toString());
            via.setText(cursor.getString(11).toString());
            status.setText(cursor.getString(12).toString());
            total.setText(cursor.getString(13).toString());
        }

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
