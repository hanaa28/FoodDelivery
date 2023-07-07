package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fooddelivery.R;

public class CongratulationActivity extends AppCompatActivity {
static String noti_key;
public static String getNoti_key(){
    return noti_key;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(), MainresActivity.class);
                    startActivity(intent);
                    noti_key="done";
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }
    }
