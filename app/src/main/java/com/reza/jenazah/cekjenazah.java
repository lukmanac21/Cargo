package com.reza.jenazah;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;



public class cekjenazah extends AppCompatActivity {

    String[] daftar,Tampil;
    ListView listView;
    protected Cursor cursor;
    connectionMYSQL dbHelper;
    EditText resi;
    Button cari;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cekjenazah);
        resi = (EditText) findViewById(R.id.resicari);
        cari = (Button) findViewById(R.id.carinoresi);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new connectionMYSQL(this);


        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil();
            }
        });
    }
    public void tampil(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM cargo WHERE no_resi = '" +
        resi.getText().toString() + "'",null);

        Tampil = new String[cursor.getCount()];
        daftar = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i =0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Tampil[i] = "No resi : " + cursor.getString(0) +
                        "\nPulau Tujuan : " + cursor.getString(7) +
                        "\nStatus : " + cursor.getString(12)
                        ;
            daftar[i] = cursor.getString(0).toString();
        }
        listView = (ListView)findViewById(R.id.listview2);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, Tampil));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                final CharSequence[] dialogitem = {"Detail"};
                AlertDialog.Builder builder = new AlertDialog.Builder(cekjenazah.this);
                builder.setTitle("Pilih Menu");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0 :
                                Intent i1 = new Intent(getApplicationContext(), edit_laporan.class);
                                i1.putExtra("no resi", selection);
                                startActivity(i1);
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

