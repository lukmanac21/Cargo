package com.reza.jenazah;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutionException;


/**
 * Created by REZA on 18/12/2017.
 */

public class tambah extends AppCompatActivity {
    connectionMYSQL dbHelper;
    EditText no_resi, nama_jenazah, umur, nama_pengirim, alamat_pengirim, no_telp, alamat_tujuan;
    Spinner pulau_tujuan, via, status,waktu;
    RadioButton lakilaki;
    RadioButton perempuan;
    DatePicker tanggal_kirim;
    Button daftar;

    hitung tarif = new hitung();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new connectionMYSQL(this);
        no_resi = (EditText) findViewById(R.id.resi1);
        nama_jenazah = (EditText)findViewById(R.id.nama_jenazah);
        lakilaki = (RadioButton) findViewById(R.id.laki_laki);
        perempuan = (RadioButton) findViewById(R.id.perempuan);
        umur = (EditText) findViewById(R.id.umur_jenazah);
        nama_pengirim = (EditText) findViewById(R.id.nama_pengirim);
        alamat_pengirim = (EditText) findViewById(R.id.alamat_pengirim);
        no_telp = (EditText) findViewById(R.id.No_telp);
        pulau_tujuan = (Spinner) findViewById(R.id.TujuanSpinner);
        alamat_tujuan = (EditText) findViewById(R.id.alamat_tujuan);
        tanggal_kirim = (DatePicker) findViewById(R.id.tanggal_kirim);
        waktu = (Spinner) findViewById(R.id.waktu_kirim);
        via = (Spinner) findViewById(R.id.ViaSpinner);
        status = (Spinner) findViewById(R.id.StatusSpinner);
        daftar = (Button) findViewById(R.id.daftar);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tgl = tanggal_kirim.getMonth()+"/"+tanggal_kirim.getDayOfMonth()+"/"+tanggal_kirim.getYear();
                String via1 = String.valueOf(via.getSelectedItem());
                String status1 = String.valueOf(status.getSelectedItem());
                String pulau = String.valueOf(pulau_tujuan.getSelectedItem());
                String waktu1 = String.valueOf(waktu.getSelectedItem());
                String jenis = "";

                if (lakilaki.isChecked()){
                    jenis = "laki laki";
                } if (perempuan.isChecked()){
                    jenis = "perempuan";
                }

                perhitungan();
                int tot_tarif = tarif.getTotal_tarif();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into cargo(no_resi, nama_jenazah, jk, umur, nama_pengirim, alamat, no_telp, pulau_tujuan, alamat_tujuan, tanggal_kirim, waktu_kirim, via, status,total_tarif) values('" +
                no_resi.getText().toString()+"','"+
                nama_jenazah.getText().toString()+"','"+
                        jenis+"','"+
                umur.getText().toString()+"','"+
                nama_pengirim.getText().toString()+"','"+
                alamat_pengirim.getText().toString()+"','"+
                no_telp.getText().toString()+"','"+
                pulau+"','"+
                alamat_tujuan.getText().toString()+"','"+
                tgl+"','"+
                waktu1+"','"+
                via1+"','"+
                status1+"','"+
                tot_tarif+"')");
                Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                admin.ma.Tampilkan();
                finish();
            }
        });
    }
    public void perhitungan(){
        try{
            tarif.setWaktu(Integer.parseInt(waktu.getSelectedItem().toString()));
            if (via.getSelectedItemPosition() == 0){
                tarif.setVia(20000000);

                if (pulau_tujuan.getSelectedItemPosition() == 0){
                    tarif.setPulau(2000000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 1) {
                    tarif.setPulau(1500000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 2) {
                    tarif.setPulau(3000000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 3) {
                    tarif.setPulau(3500000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 4) {
                    tarif.setPulau(2500000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 5) {
                    tarif.setPulau(4000000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 6) {
                    tarif.setPulau(5000000);
                }
            } else if (via.getSelectedItemPosition() == 1) {
                tarif.setVia(10000000);

                if (pulau_tujuan.getSelectedItemPosition() == 0) {
                    tarif.setPulau(2000000);
                } else if (pulau_tujuan.getSelectedItemPosition() == 1) {
                    tarif.setPulau(1500000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 2) {
                    tarif.setPulau(3000000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 3) {
                    tarif.setPulau(3500000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 4) {
                    tarif.setPulau(2500000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 5) {
                    tarif.setPulau(4000000);
                }
                else if (pulau_tujuan.getSelectedItemPosition() == 6) {
                    tarif.setPulau(5000000);
                }

            }
            tarif.setTotal_tarif(tarif.getPulau(),tarif.getWaktu(),tarif.getVia(),tarif.getKurir(), tarif.getBerat());

        }catch (Exception e){
            e.printStackTrace();
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