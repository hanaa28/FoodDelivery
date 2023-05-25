package com.example.fooddelivery.activities.avtivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.adapter.OrderAdapter;
import com.example.fooddelivery.activities.interfaces.RestaurantInterface;
import com.example.fooddelivery.activities.interfaces.UserInterface;
import com.example.fooddelivery.activities.models.ErrorResponse;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.LoginModel;
import com.example.fooddelivery.activities.models.UserResponse;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.ActivityOrderBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderActivity extends AppCompatActivity {
ActivityOrderBinding binding;
OrderAdapter orderAdapter;
RecyclerView orderRV;
    private ArrayList<FoodResponse> orderList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);
        orderRV= binding.orderRecycler;
        RestaurantInterface res = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
        HashMap<String, String> foodList = sharedPrefs.getOrder();
        for (Map.Entry<String, String> item :
                foodList.entrySet()) {
            System.out.println(item.getKey());
        }
//        orderList.add(new Food(R.drawable.logo, "Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo, "Spacy fresh crab", "Waroenk kita", 35));
//        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));

        generateDataList(orderList);

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

    }


    private void generateDataList(ArrayList<FoodResponse> orderList) {

        orderAdapter = new OrderAdapter( orderList,this);
        binding.orderRecycler.setAdapter(orderAdapter);
        binding.orderRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}