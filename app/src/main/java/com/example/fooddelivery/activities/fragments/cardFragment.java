package com.example.fooddelivery.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.Constants;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.adapter.OrderAdapter;
import com.example.fooddelivery.activities.avtivities.LoginActivity;
import com.example.fooddelivery.activities.avtivities.MainresActivity;
import com.example.fooddelivery.activities.interfaces.RestaurantInterface;
import com.example.fooddelivery.activities.models.ErrorResponse;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.UserResponse;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.FragmentCardBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cardFragment extends Fragment {

    FragmentCardBinding binding;
    OrderAdapter orderAdapter;
    RecyclerView orderRV;
    private ArrayList<FoodResponse> orderList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false);
        orderRV= binding.orderRecycler;
        RestaurantInterface res = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        SharedPrefs sharedPrefs = new SharedPrefs(requireContext());
        HashMap<String, String> foodList = new HashMap<>();
        String foods = sharedPrefs.getOrder().get(Constants.KEY_FOOD);
        if (foods != null) {
            String[] foods2 = foods.substring(1, foods.length()-1).split(",");
            for (String s: foods2) {
                String[] temp = s.split("=");
                foodList.put(temp[0].trim(), temp[1]);
            }
        }
        for (Map.Entry<String, String> item :
                foodList.entrySet()) {
            Call<FoodResponse> call = res.getOneFood("Bearer "+LoginActivity.getToken(), item.getKey());
            System.out.println("///////////"+call.request());
            call.enqueue(new Callback<FoodResponse>() {

                @Override
                public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                    System.out.println("**********************");
                    System.out.println(response.code());
                    if(response.isSuccessful()) {
                        System.out.println("**********************");
                        System.out.println(response);
                        orderList.add(response.body());
                        orderAdapter.notifyItemInserted(orderAdapter.getItemCount());
                    }
                    else  {
                    }
                }
                @Override
                public void onFailure(@NonNull Call<FoodResponse> call, @NonNull Throwable t) {

                }
            });
        }
        generateDataList(orderList);
//        orderList.add(new Food(R.drawable.logo, "Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo, "Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                FoodResponse deleteditem = orderList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                orderList.remove(viewHolder.getAdapterPosition());
                orderAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Snackbar.make(orderRV, deleteditem.getData().get(0).getName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderList.add(position, deleteditem);

                        orderAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(binding.orderRecycler);
        return binding.getRoot();
    }


    private void generateDataList(ArrayList<FoodResponse> orderList) {

        orderAdapter = new OrderAdapter( orderList,getContext());
        binding.orderRecycler.setAdapter(orderAdapter);
        binding.orderRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}