package com.example.fooddelivery.activities.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.fooddelivery.activities.adapter.OrderHolder;
import com.example.fooddelivery.activities.avtivities.LastOrderActivity;
import com.example.fooddelivery.activities.avtivities.LoginActivity;
import com.example.fooddelivery.activities.avtivities.MainresActivity;
import com.example.fooddelivery.activities.interfaces.RestaurantInterface;
import com.example.fooddelivery.activities.models.ErrorResponse;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.UserResponse;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.FragmentCardBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
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
    static int count;
    private ArrayList<FoodResponse> orderList = new ArrayList<>();
    public static int geettotal(){

        return count;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false);

        orderRV= binding.orderRecycler;


        RestaurantInterface res = RetrofitClient.getRetrofitInstance().create(RestaurantInterface.class);
        SharedPrefs sharedPrefs = new SharedPrefs(requireContext());
        HashMap<String, String> foodList = new HashMap<>();
        String foods = sharedPrefs.getOrder().get(Constants.KEY_FOOD);

        System.out.println(foods + "_________________________");
        if (foods != null && !foods.equals("null")) {
            String[] foods2 = foods.substring(1, foods.length()-1).split(",");
            for (String s: foods2) {
                String[] temp = s.split("=");
                foodList.put(temp[0].trim(), temp[1]);
            }
        }
        for (Map.Entry<String, String> item :
                foodList.entrySet()) {
            Call<FoodResponse> call = res.getOneFood("Bearer "+RestaurantFragment.Token(), item.getKey());
            System.out.println("///////////"+call.request());
            call.enqueue(new Callback<FoodResponse>() {

                @Override
                public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                    System.out.println("**********************");
                    System.out.println(response.code());
                    if(response.isSuccessful()) {
                        System.out.println("**********************");
                        System.out.println(response);
                        response.body().getData().get(0).setQun(Integer.parseInt(item.getValue()));
                        orderList.add(response.body());
                        System.out.println("henaaa"+orderList.get(0));




                       binding.accept.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {


                               Intent i=new Intent(getActivity(), LastOrderActivity.class);
                               startActivity(i);
                               count=orderAdapter.gettotal();
                           }

                       });

//                        for (int i=0;i<=orderList.size();i++) {
//                            int price=0;
//                            if (orderList.size()==0){
//                                binding.price.setText(0);
//                            }
//                            else if (orderList.size()==1) {
//                               //  price=Integer.parseInt(response.body().getData().get(0).getPrice())*response.body().getData().get(0).getQun();
//                                binding.price.setText(orderAdapter.g);
//                            }
//                            else{
//                                price +=Integer.parseInt(response.body().getData().get(i).getPrice())*response.body().getData().get(i).getQun();
//                                 binding.price.setText(String.valueOf(price));
//                            }

//                        }

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
                FoodResponse deletedItem = orderList.get(viewHolder.getBindingAdapterPosition());
                System.out.println(viewHolder.getBindingAdapterPosition());
                System.out.println(deletedItem.getData().get(0).getId());
                int position = viewHolder.getBindingAdapterPosition();
                System.out.println(deletedItem + "_______________________________________");
                orderList.remove(viewHolder.getBindingAdapterPosition());
                orderAdapter.notifyItemRemoved(viewHolder.getBindingAdapterPosition());
                Snackbar.make(requireActivity().findViewById(R.id.recyclerView), deletedItem.getData().get(0).getName(), Snackbar.LENGTH_LONG).setAction("Undo", v -> {
                    orderList.add(position, deletedItem);

                    orderAdapter.notifyItemInserted(position);
                }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        SharedPrefs sharedPrefs = new SharedPrefs(requireContext());
                        System.out.println(deletedItem.getData().get(0).getId());
                        sharedPrefs.setOrder(String.valueOf(deletedItem.getData().get(0).getId()), 0);
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
        orderAdapter.notifyDataSetChanged();


    }
//    public int geettotal(){
//     return orderAdapter.gettotal();
//    }
}