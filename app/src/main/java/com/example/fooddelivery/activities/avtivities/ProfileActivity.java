package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.View.FoodViewModel;
import com.example.fooddelivery.activities.fragments.ProfileFragment;
import com.example.fooddelivery.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        ProfileFragment profileFragment=new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(binding.pframeLayout.getId(),
         new FoodViewModel().newInstance(LinearLayoutManager.VERTICAL)).commit();

    }
}