package com.example.fooddelivery.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    private int quantity = 1;
    private ArrayList<FoodResponse> orderArrayList;
    private Context context;

    // creating a constructor class.
    public OrderAdapter(ArrayList<FoodResponse> orderArrayList, Context context) {
        this.orderArrayList = orderArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View orderView = inflater.inflate(R.layout.order_item, parent, false);
        return new OrderHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {

        FoodResponse order=orderArrayList.get(position);
        holder.Ofoodname.setText(order.getData().get(0).getName());
        holder.Odescription.setText(order.getData().get(0).getDescription());
        holder.Oprice.setText(order.getData().get(0).getPrice());
        System.out.println(order.getData().get(0).getPic());
//        Picasso.get().load(order.getData().get(0).getPic()).into(holder.OImageFood, new Callback() {
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Picasso.get().load(R.drawable.logo).into(holder.OImageFood);
//            }
//        });
        Picasso.get().load(R.drawable.logo).error(R.drawable.logo).into(holder.OImageFood);
        holder.Oplue.setOnClickListener((view) -> {
            int qun = Integer.parseInt(holder.Onum.getText().toString()) + 1;
            holder.Onum.setText(String.valueOf(qun));
        });
        holder.Ominus.setOnClickListener(View ->{
            int qun = Integer.parseInt(holder.Onum.getText().toString());
            if (qun>1){
                qun -=1;
                holder.Onum.setText(String.valueOf(qun));
            }

        });


    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }
}
