package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.View.FoodViewModel;
import com.example.fooddelivery.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

        ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignActivity.class);
                startActivity(intent);
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainresActivity.class);
                startActivity(intent);
//                getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),
//                        new FoodViewModel().newInstance(LinearLayoutManager.VERTICAL)).commitAllowingStateLoss();

            }
        });


    }
}