package com.example.fooddelivery.activities.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.databinding.OrderItemBinding;
import com.example.fooddelivery.databinding.ResfoodListItemBinding;

public class OrderHolder extends RecyclerView.ViewHolder {
    OrderItemBinding binding;
    TextView Oprice,Ofoodname,Odescription,Onum;
    ImageView OImageFood;
    ImageButton Oplue,Ominus;
    public OrderHolder(@NonNull View itemView) {
        super(itemView);
       binding = OrderItemBinding.bind(itemView);
        Odescription=binding.desorderfood;
        Ofoodname=binding.FoodName;
        Oprice=binding.orderprice;
        Onum=binding.foodnum;
        OImageFood=binding.orderFood;
        Oplue=binding.plus;
        Ominus=binding.minus;
    }
}
