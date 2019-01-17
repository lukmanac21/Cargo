package com.reza.jenazah;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by REZA on 18/12/2017.
 */

public class edit extends AppCompatActivity {

    protected Cursor cursor;
    connectionMYSQL dbHelper;
    Spinner via,status;
    TextView resi;
    EditText  namajenazah, umur, jk, namapengirim, alamatpengirim, notelp, pulau, alamattujuan, tanggal, waktu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new connectionMYSQL(this);
        resi = (TextView) findViewById(R.id.textresi);
        namajenazah = (EditText) findViewById(R.id.editjenazah);
        umur = (EditText) findViewById(R.id.editumur);
        jk = (EditText) findViewById(R.id.editjk);
        namapengirim = (EditText) findViewById(R.id.editpengirim);
        alamatpengirim = (EditText) findViewById(R.id.editalp);
        notelp = (EditText) findViewById(R.id.edittelp);
        pulau = (EditText) findViewById(R.id.editpulau);
        alamattujuan = (EditText) findViewById(R.id.editalt);
        tanggal = (EditText) findViewById(R.id.edittanggal);
        waktu = (EditText) findViewById(R.id.editwaktu);
        via = (Spinner) findViewById(R.id.editvia);
        status = (Spinner) findViewById(R.id.editstatus);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM cargo WHERE no_resi = '" +
                getIntent().getStringExtra("no resi") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            resi.setText(cursor.getString(0).toString());
            namajenazah.setText(cursor.getString(1).toString());
            jk.setText(cursor.getString(2).toString());
            umur.setText(cursor.getString(3).toString());
            namapengirim.setText(cursor.getString(4).toString());
            alamatpengirim.setText(cursor.getString(5).toString());
            notelp.setText(cursor.getString(6).toString());
            pulau.setText(cursor.getString(7).toString());
            alamattujuan.setText(cursor.getString(8).toString());
            tanggal.setText(cursor.getString(9).toString());
            waktu.setText(cursor.getString(10).toString());
        }
        Button simpan = (Button)findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update cargo set nama_jenazah='" +
                        namajenazah.getText().toString() + "', jk='" +
                        jk.getText().toString() + "', umur='" +
                        umur.getText().toString() + "', nama_pengirim='" +
                        namapengirim.getText().toString() + "', alamat='" +
                        alamatpengirim.getText().toString() + "', no_telp='" +
                        notelp.getText().toString() + "', pulau_tujuan='" +
                        pulau.getText().toString() + "', alamat_tujuan='" +
                        alamattujuan.getText().toString() + "', tanggal_kirim='" +
                        tanggal.getText().toString() + "', waktu_kirim='" +
                        waktu.getText().toString() + "', via='" +
                        via.getSelectedItem().toString() + "', status='" +
                        status.getSelectedItem().toString() + "'where no_resi='" +
                        resi.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                admin.ma.Tampilkan();
                finish();

            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

