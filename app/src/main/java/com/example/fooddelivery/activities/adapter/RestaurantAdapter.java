package com.example.fooddelivery.activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.avtivities.LoginActivity;
import com.example.fooddelivery.activities.avtivities.MainresActivity;
import com.example.fooddelivery.activities.avtivities.MenuActivity;
import com.example.fooddelivery.activities.models.RestaurantResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder> {
    RestaurantResponse restaurantResponse;
    Context context;

    public RestaurantAdapter(RestaurantResponse restaurantResponse, Context context) {
        this.restaurantResponse = restaurantResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.food_list_item, parent, false);

        return new RestaurantHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
        holder.restaurantName.setText(restaurantResponse.getData().get(position).getTags1());
        Picasso.get().load(restaurantResponse.getData().get(position).getPic()).error(R.drawable.image).into(holder.restaurantImage);
        holder.restaurantImage.setOnClickListener(v -> onImageClick(v, restaurantResponse.getData().get(position).getId()));
    }

    void onImageClick(View v,int id) {
        Intent intent = new Intent(v.getContext(), MenuActivity.class);
        intent.putExtra("id", id);
        System.out.println("///////"+id);
        v.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        System.out.println("kkk"+restaurantResponse.getData().size());
        return restaurantResponse.getData().size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
