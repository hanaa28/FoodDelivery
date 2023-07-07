package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.Constants;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.adapter.OrderAdapter;
import com.example.fooddelivery.activities.fragments.cardFragment;
import com.example.fooddelivery.databinding.ActivityLastOrderBinding;


public class LastOrderActivity extends AppCompatActivity {
    ActivityLastOrderBinding binding;
    private SharedPrefs sharedPrefs;
    private String location;
    OrderAdapter order;
    static String edit_key;
    public static String geteditkey(){
        return edit_key;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_last_order);
        System.out.println("LLLLLL"+LocationActivity.getLocation());
        sharedPrefs = new SharedPrefs(getApplicationContext());
        location = sharedPrefs.getlocation().get(Constants.KEY_LOCATION);
        binding.llocation.setText(location);
        System.out.println("LLLLLL"+location);
//        binding.subtot.setText(String.valueOf(cardFragment.geettotal()));

        binding.subtot.setText(String.valueOf(cardFragment.geettotal()));
        binding.total.setText(String.valueOf(get_total()));
        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LastOrderActivity.this,LocationActivity.class);
                startActivity(i);
                edit_key="yes";

            }
        });
        binding.acceptt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LastOrderActivity.this,CongratulationActivity.class);
                startActivity(i);
            }
        });


    }
    public int get_total(){
        return Integer.parseInt(String.valueOf(binding.subtot.getText()))+
                Integer.parseInt(String.valueOf(binding.delprice.getText()))-Integer.parseInt(String.valueOf(binding.dis.getText()));
    }
}