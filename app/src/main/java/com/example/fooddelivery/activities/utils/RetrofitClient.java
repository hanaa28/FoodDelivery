package com.example.fooddelivery.activities.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static final String Base_URL = "http://206.189.103.8/api/";

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
