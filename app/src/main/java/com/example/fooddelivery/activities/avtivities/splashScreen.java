package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.avtivities.MainActivity;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread=new Thread(){
            @Override
            public void run() {
             try {
                 sleep(5000);
                 Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(intent);
                 finish();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

            }
        };
        thread.start();

    }
}