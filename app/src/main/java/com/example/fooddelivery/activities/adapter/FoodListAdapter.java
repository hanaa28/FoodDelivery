package com.example.fooddelivery.activities.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.pojo.FoodModel;
import com.example.fooddelivery.databinding.FoodListItemBinding;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    FoodListItemBinding binding;
    public final Context mContext;
    private ArrayList<FoodModel> foodList=new ArrayList<>();

    public FoodListAdapter(Context mContext ,ArrayList<FoodModel>list) {
        this.mContext = mContext;
        this.foodList=list;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("Adapter","onCreateViewHolder Adapter");
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.food_list_item, parent, false);

        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Log.e("Adapter","onBindViewHolder Adapter");
        FoodModel foodModel=foodList.get(position);
        if (foodModel != null){
            if (foodModel.getImage1()!=null){
                 Glide
                        .with(mContext)
                        .load(foodModel.getImage1())
                        .centerCrop()
                        .into(binding.resphoto);
            }

            binding.nameres.setText(foodModel.getRESName1());
            if (foodModel.getImage2()!=null){
                Glide
                        .with(mContext)
                        .load(foodModel.getImage2())
                        .centerCrop()
                        .into(binding.res2pgoto);
            }
            binding.snameres.setText(foodModel.getRESName2());



    }




}

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        FoodListItemBinding itemBinding;
        public FoodViewHolder(FoodListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;

        }
    }
}
