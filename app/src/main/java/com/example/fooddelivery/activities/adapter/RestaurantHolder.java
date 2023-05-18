package com.example.fooddelivery.activities.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FoodListItemBinding;

public class RestaurantHolder extends RecyclerView.ViewHolder {
    TextView restaurantName;
    FoodListItemBinding binding;
    ImageButton restaurantImage;
    public RestaurantHolder(@NonNull View itemView) {
        super(itemView);
//        binding = DataBindingUtil.inflate(LayoutInflater.from(getA()), itemView, false)
        restaurantName = itemView.findViewById(R.id.nameres);
        restaurantImage = itemView.findViewById(R.id.resphoto);
    }
}
