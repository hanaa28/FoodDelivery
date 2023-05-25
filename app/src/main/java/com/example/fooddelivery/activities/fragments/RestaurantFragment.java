package com.example.fooddelivery.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.avtivities.LoginActivity;
import com.example.fooddelivery.activities.interfaces.RestaurantInterface;
import com.example.fooddelivery.activities.models.RestaurantResponse;
import com.example.fooddelivery.activities.adapter.RestaurantAdapter;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.FragmentResturantBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantFragment extends Fragment {

    FragmentResturantBinding binding;
    Intent intent;

    RestaurantAdapter restaurantAdapter;


    public RestaurantFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        intent = requireActivity().getIntent();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_resturant, container, false);
        RestaurantInterface restaurantInterface = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        Call<RestaurantResponse> call = restaurantInterface.getRestaurant("Bearer " + LoginActivity.getToken());
        System.out.println("*****************");
        System.out.println(LoginActivity.getToken());
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                System.out.println(response.body());
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }
    private void generateDataList(RestaurantResponse restaurantResponse) {
        restaurantAdapter = new RestaurantAdapter(restaurantResponse, getContext());
        binding.recycler.setAdapter(restaurantAdapter);
        binding.recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }
}