package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.View.FoodViewModel;
import com.example.fooddelivery.databinding.ActivityFrestrantBinding;
import com.example.fooddelivery.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    ActivityFrestrantBinding binding2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        binding.nextmain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this,LoginActivity.class);
                startActivity(intent);
            }
        });

//        binding.nextmain2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),
//                        new FoodViewModel().newInstance(LinearLayoutManager.VERTICAL)).commitAllowingStateLoss();
//
//            }
//        });


    }
}