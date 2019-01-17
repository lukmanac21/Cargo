package com.reza.jenazah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login1 extends AppCompatActivity {
    protected Cursor cursor;
    connectionLogin dbHelper;
    EditText username,password;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        dbHelper = new connectionLogin(this);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button masuk = (Button) findViewById(R.id.masuk);
        masuk.setOnClickListener(new klik());
    }
    class klik implements Button.OnClickListener{
        public void onClick (View v){
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            cursor = db.rawQuery("SELECT * FROM login",null);
//            cursor.moveToFirst();
//            String user = cursor.getString(cursor.getColumnIndex("username")).toString();
//            String pass = cursor.getString(cursor.getColumnIndex("password")).toString();
            String user = "admin";
            String pass = "admin1";
            if (user.equals(username.getText().toString()) && pass.equals(password.getText().toString())){
                Intent i = new Intent(login1.this, admin.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), user+"-"+pass, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
