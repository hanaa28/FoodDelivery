package com.example.fooddelivery.activities.interfaces;

import com.example.fooddelivery.activities.models.ErrorResponse;
import com.example.fooddelivery.activities.models.LoginModel;
import com.example.fooddelivery.activities.models.RegisterModel;
import com.example.fooddelivery.activities.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("auth/login")
    Call<UserResponse> loginUser(@Body LoginModel loginModel);

    @POST("auth/register/user")
    Call<UserResponse> registerUser(@Body RegisterModel registerModel);
}
