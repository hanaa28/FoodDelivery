package com.example.fooddelivery.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.adapter.FoodAdapter;
import com.example.fooddelivery.activities.avtivities.LoginActivity;
import com.example.fooddelivery.activities.avtivities.SignActivity;
import com.example.fooddelivery.activities.interfaces.RestaurantInterface;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.Restaurant;
import com.example.fooddelivery.activities.models.RestaurantResponse;
import com.example.fooddelivery.activities.adapter.RestaurantAdapter;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.FragmentResturantBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantFragment extends Fragment {

    FragmentResturantBinding binding;
    Intent intent;
    FoodAdapter foodAdapter2;

    RestaurantAdapter restaurantAdapter;
    androidx.appcompat.widget.SearchView search;
    List<RestaurantResponse>arr=new ArrayList<>();
    public static String Token() {
        if (SignActivity.getToken()!=null)
            return SignActivity.getToken();
        if (LoginActivity.getToken()!=null)
            return LoginActivity.getToken();
        return null;
    }

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
        search=binding.search;

        search.clearFocus();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });

        RestaurantInterface restaurantInterface = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        Call<RestaurantResponse> call = restaurantInterface.getRestaurant("Bearer " + Token());

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

    private void filterlist(String text) {
        RestaurantInterface restaurantInterface = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        Call<FoodResponse> call = restaurantInterface.getFoodbyname("Bearer " + RestaurantFragment.Token(),text);
        System.out.println("get food name");
        System.out.println(LoginActivity.getToken());
        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
              if(response.body() != null){
                List<Food> data = new ArrayList<>();
                List<Food> res = response.body().getData();
                for (int i = 0; i < res.size(); i++) {
                    if (res.get(i).getName().equals(String.valueOf(text))) {
                        data.add(res.get(i));
                        System.out.println("helllo"+res.get(i).getName());
                    }
                }
                response.body().setData(data);
                generateDataList2(response.body());
            }
            else{
                  RestaurantInterface restaurantInterface = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
                  Call<RestaurantResponse> call1 = restaurantInterface.getRestaurant("Bearer " + LoginActivity.getToken());
                  System.out.println("*****************");
                  System.out.println(LoginActivity.getToken());
                  call1.enqueue(new Callback<RestaurantResponse>() {
                      @Override
                      public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                          System.out.println(response.body());
                          generateDataList(response.body());
                          restaurantAdapter.notifyItemInserted(restaurantAdapter.getItemCount());

                      }

                      @Override
                      public void onFailure(Call<RestaurantResponse> call, Throwable t) {

                      }
                  });
              }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {

            }
        });
    }

    private void generateDataList(RestaurantResponse restaurantResponse) {
        restaurantAdapter = new RestaurantAdapter(restaurantResponse, getContext());
        binding.recycler1.setAdapter(restaurantAdapter);
        binding.recycler1.setLayoutManager(new GridLayoutManager(getContext(),2));

//       int i=0;
//       while (i< restaurantResponse.getData().size()){
//       arr.add(restaurantResponse);
//        System.out.println("------------------------------------------"+arr.size());
//        System.out.println(arr.get(i));
//       i+=1;}
//        System.out.println("------------------------------------------"+arr.size());
//


  }
    private void generateDataList2(FoodResponse foodResponse) {
        foodAdapter2 = new FoodAdapter(foodResponse,getContext());
        binding.recycler1.setAdapter(foodAdapter2);
        binding.recycler1.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}