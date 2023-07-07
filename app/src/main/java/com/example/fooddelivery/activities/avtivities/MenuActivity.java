package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.adapter.FoodAdapter;
import com.example.fooddelivery.activities.adapter.RestaurantAdapter;
import com.example.fooddelivery.activities.fragments.FoodFragment;
import com.example.fooddelivery.activities.fragments.RestaurantFragment;
import com.example.fooddelivery.activities.interfaces.RestaurantInterface;
import com.example.fooddelivery.activities.interfaces.UserInterface;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.LoginModel;
import com.example.fooddelivery.activities.models.RestaurantResponse;
import com.example.fooddelivery.activities.models.UserResponse;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.ActivityMenuBinding;
import com.example.fooddelivery.databinding.FragmentResturantBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    
    ActivityMenuBinding binding;
    FoodAdapter foodAdapter;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        id = getIntent().getExtras().getInt("id");
        RestaurantInterface food = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        Call<FoodResponse> call = food.getFood("Bearer " + RestaurantFragment.Token());
        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {

                assert response.body() != null;
                List<Food> data = new ArrayList<>();
                List<Food> res = response.body().getData();
                for (int i = 0; i < res.size(); i++) {
                    if (res.get(i).getRestaurentId().equals(String.valueOf(id))) {
                       data.add(res.get(i));
                        System.out.println("helllo"+res.get(i).getName());
                    }
                }
                response.body().setData(data);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {

            }
        });
    }
    private void generateDataList(FoodResponse foodResponse) {
        foodAdapter = new FoodAdapter(foodResponse, this);
        binding.recycler.setAdapter(foodAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}