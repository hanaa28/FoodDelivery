package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.View.FoodViewModel;
import com.example.fooddelivery.activities.fragments.ProfileFragment;
import com.example.fooddelivery.activities.fragments.cardFragment;
import com.example.fooddelivery.activities.fragments.notifcationFragment;
import com.example.fooddelivery.databinding.ActivityMainresBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainresActivity extends AppCompatActivity {
ActivityMainresBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mainres);

        getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),
                        new FoodViewModel().newInstance(LinearLayoutManager.VERTICAL)).commit();

        binding.bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),
                                new FoodViewModel().newInstance(LinearLayoutManager.VERTICAL)).commit();
                        return true;
//
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),
                                new ProfileFragment()).commit();
                        return true;
                    case R.id.noti:
                    getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),
                            new notifcationFragment()).commit();
                    return true;
                case R.id.card:
                    getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),new cardFragment()).commit();
                    return true;

                }
                return false;
            }
        });

    }}
