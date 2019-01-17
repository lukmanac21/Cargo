package com.reza.jenazah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by REZA on 19/12/2017.
 */

public class splash extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread timer = new Thread(){
            public  void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
