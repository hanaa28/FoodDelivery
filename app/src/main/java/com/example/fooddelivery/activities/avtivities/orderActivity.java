package com.example.fooddelivery.activities.avtivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.adapter.OrderAdapter;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.databinding.ActivityOrderBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class orderActivity extends AppCompatActivity {
ActivityOrderBinding binding;
OrderAdapter orderAdapter;
RecyclerView orderRV;
    private ArrayList<Food> orderList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);
        orderRV= binding.orderRecycler;
        orderList.add(new Food(R.drawable.logo, "Spacy fresh crab", "Waroenk kita", 35));
        orderList.add(new Food(R.drawable.logo, "Spacy fresh crab", "Waroenk kita", 35));
        orderList.add(new Food(R.drawable.logo ,"Spacy fresh crab", "Waroenk kita", 35));

        generateDataList(orderList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Food deleteditem = orderList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                orderList.remove(viewHolder.getAdapterPosition());
                orderAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Snackbar.make(orderRV, deleteditem.getName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderList.add(position, deleteditem);

                        orderAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(binding.orderRecycler);

    }


    private void generateDataList(ArrayList<Food> orderList) {

        orderAdapter = new OrderAdapter( orderList,this);
        binding.orderRecycler.setAdapter(orderAdapter);
        binding.orderRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}