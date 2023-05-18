package com.example.fooddelivery.activities.models;

import com.example.fooddelivery.activities.interfaces.UserInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String Base_URL="http://143.244.197.131/api/";
    private UserInterface retrofitApi;
    private static ApiClient INSTANCE;

    public void loginUser(String email, String password){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
