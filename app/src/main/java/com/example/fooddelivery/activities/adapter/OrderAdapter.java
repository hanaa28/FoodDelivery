package com.example.fooddelivery.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.models.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    private int quantity = 1;
    private ArrayList<Food> orderArrayList;
    private Context mcontext;

    // creating a constructor class.
    public OrderAdapter(ArrayList<Food> orderArrayList, Context mcontext) {
        this.orderArrayList = orderArrayList;
        this.mcontext = mcontext;
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
        Food order=orderArrayList.get(position);
        holder.Ofoodname.setText(order.getName());
        holder.Odescription.setText(order.getDescription());
        holder.Oprice.setText(order.getPrice());
        Picasso.get().load(order.getPic()).error(R.drawable.baseline_home_24).into(holder.OImageFood);
        holder.Oplue.setOnClickListener(view -> {
            quantity += 1;
            holder.Onum.setText(String.valueOf(quantity));
        });
        holder.Ominus.setOnClickListener(View ->{
            if (quantity>1){
                quantity -=1;
                holder.Onum.setText(String.valueOf(quantity));
            }

        });


    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }
}
