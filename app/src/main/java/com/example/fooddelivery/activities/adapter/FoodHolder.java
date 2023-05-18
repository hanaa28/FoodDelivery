package com.example.fooddelivery.activities.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FoodListItemBinding;
import com.example.fooddelivery.databinding.ResfoodListItemBinding;

public class FoodHolder extends RecyclerView.ViewHolder {
    TextView foodName, description, price;
    ResfoodListItemBinding binding;
    ImageView foodImage;

    public FoodHolder(@NonNull View itemView) {
        super(itemView);

      binding = ResfoodListItemBinding.bind(itemView);
        foodName = binding.FoodName;
        foodImage = binding.ImageFood;
        description = binding.desfood;
        price=binding.price;
    }
}