package com.reza.jenazah;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;




public class admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    String[] daftar, TamList;
    ListView listView;
    protected Cursor cursor;
    connectionMYSQL dbcenter;
    Button otw,semua,belum,terkirim;
    public static admin ma;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        ma = this;
        dbcenter = new connectionMYSQL(this);
        Tampilkan();

    }
    public void Tampilkan() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM cargo",null);
        TamList = new String[cursor.getCount()];
        daftar = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i =0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            TamList[i] = "No resi : " + cursor.getString(0).toString() +
                         "\nPulau Tujuan : " + cursor.getString(7) +
                         "\nStatus : " + cursor.getString(12).toString();
            daftar[i] =  cursor.getString(0).toString();
        }
        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, TamList));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                final CharSequence[] dialogitem = {"Detail", "Edit", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(admin.this);
                builder.setTitle("Pilih Menu");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0 :
                                Intent i1 = new Intent(getApplicationContext(), laporan.class);
                                i1.putExtra("no resi", selection);
                                startActivity(i1);
                                break;
                            case 1 :
                                Intent i2 = new Intent(getApplicationContext(), edit.class);
                                i2.putExtra("no resi", selection);
                                startActivity(i2);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from cargo where no_resi = '"+selection+"'");
                                Tampilkan();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id = item.getItemId();
        android.app.FragmentManager atur = getFragmentManager();

        if (id == R.id.nav_keluar1){
            this.finish();
        }else if (id == R.id.nav_tambah){
            Intent intent = new Intent(admin.this, tambah.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
