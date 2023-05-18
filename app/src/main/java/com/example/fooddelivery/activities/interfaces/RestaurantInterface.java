package com.example.fooddelivery.activities.interfaces;

import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.LoginModel;
import com.example.fooddelivery.activities.models.RestaurantResponse;
import com.example.fooddelivery.activities.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RestaurantInterface {
    @GET("restaurent")
    Call<RestaurantResponse> getRestaurant(@Header("Authorization") String token);

    @GET("food")
    Call<FoodResponse> getFood(@Header("Authorization") String token);
}
