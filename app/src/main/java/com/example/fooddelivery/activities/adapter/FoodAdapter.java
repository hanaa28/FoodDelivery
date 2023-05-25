package com.example.fooddelivery.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.activities.models.RestaurantResponse;
import com.squareup.picasso.Picasso;

public class FoodAdapter  extends RecyclerView.Adapter<FoodHolder> {

    FoodResponse foodResponse;
    Context context;
    public FoodAdapter(FoodResponse foodResponse, Context context) {
        this.foodResponse = foodResponse;
        this.context = context;
    }
    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.resfood_list_item, parent, false);
        return new FoodHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
            holder.foodName.setText(foodResponse.getData().get(position).getName());
            holder.price.setText(foodResponse.getData().get(position).getPrice());
            holder.description.setText(foodResponse.getData().get(position).getDescription());
        Picasso.get().load(foodResponse.getData().get(position).getPic()).error(R.drawable.baseline_home_24).into(holder.foodImage);
        holder.addToCart.setOnClickListener(v -> {
            System.out.println("**************************");
            System.out.println(foodResponse.getData().get(position).getId());
            SharedPrefs sharedPrefs = new SharedPrefs(context);
            sharedPrefs.setOrder(String.valueOf(foodResponse.getData().get(position).getId()),"1", 1);
        });
    }

    @Override
    public int getItemCount() {
        return foodResponse.getData().size();
    }
}
