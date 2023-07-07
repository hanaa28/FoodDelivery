package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.fragments.ProfileFragment;
import com.example.fooddelivery.activities.fragments.RestaurantFragment;
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
        getSupportFragmentManager().beginTransaction().replace(binding.recyclerView.getId(),
                new RestaurantFragment()).commit();
        binding.bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(binding.recyclerView.getId(),
                                new RestaurantFragment()).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(binding.recyclerView.getId(),
                                new ProfileFragment()).commit();
                        return true;
                    case R.id.noti:
                        getSupportFragmentManager().beginTransaction().replace(binding.recyclerView.getId(),
                                new notifcationFragment()).commit();
                        return true;
                    case R.id.card:
                        getSupportFragmentManager().beginTransaction().replace(binding.recyclerView.getId(),new cardFragment()).commit();
                        return true;

                }
                return false;
            }
        });
//        populateRecyclerView();

//          FoodListAdapter adapter=new FoodListAdapter(MainresActivity.this, FoodModel,false);
    }

//        private void populateRecyclerView(List<FoodModel> foodList) {
//            adapter = new FoodListAdapter(MainresActivity.this, foodList ,false);
//            RecyclerView recyclerView=findViewById(R.id.recyclerView);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adapter);

//            binding.rvArticles.setHasFixedSize(true);
//            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
//            binding.rvArticles.setLayoutManager(mLayoutManager);
//            binding.rvArticles.setItemAnimator(new DefaultItemAnimator());
//            articlesAdapter.setHasStableIds(true);
//            binding.rvArticles.setAdapter(articlesAdapter);

        //}

//
      // getSupportFragmentManager().beginTransaction().replace(binding.frameLayout.getId(),new FoodViewModel().newInstance(LinearLayoutManager.VERTICAL)).commit();



    }
