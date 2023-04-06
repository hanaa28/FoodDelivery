package com.example.fooddelivery.activities.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.adapter.FoodListAdapter;
import com.example.fooddelivery.activities.config;
import com.example.fooddelivery.activities.pojo.FoodModel;
import com.example.fooddelivery.databinding.ActivityFrestrantBinding;

import java.util.ArrayList;

public class FoodViewModel extends Fragment {
    private int orientation;

    public Fragment newInstance(int orientation){
        Fragment fragment = new FoodViewModel();
        Bundle bundle= new Bundle();
        bundle.putInt(config.ORIENTATION, orientation);
        fragment.setArguments(bundle);
        return fragment;
    }
    private ActivityFrestrantBinding binding;
    ArrayList<FoodModel> arrayList;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_frestrant, container, false);
        Bundle bundle = getArguments();
        if (bundle !=null && bundle.containsKey(config.ORIENTATION)){
            orientation= bundle.getInt(config.ORIENTATION);
        }
        arrayList=new ArrayList<>();

        arrayList.add(new FoodModel(1, getResources().getDrawable(R.drawable.fres),getResources().getDrawable(R.drawable.fres),"Vegan Resto","Vegan Resto"));
        arrayList.add(new FoodModel(2, getResources().getDrawable(R.drawable.sres),getResources().getDrawable(R.drawable.sres),"Vegan Resto","Vegan Resto"));
        arrayList.add(new FoodModel(3, getResources().getDrawable(R.drawable.sres),getResources().getDrawable(R.drawable.sres),"Vegan Resto","Vegan Resto"));
        arrayList.add(new FoodModel(4, getResources().getDrawable(R.drawable.sres),getResources().getDrawable(R.drawable.sres),"Vegan Resto","Vegan Resto"));
        arrayList.add(new FoodModel(5, getResources().getDrawable(R.drawable.sres),getResources().getDrawable(R.drawable.sres),"Vegan Resto","Vegan Resto"));
        arrayList.add(new FoodModel(6, getResources().getDrawable(R.drawable.sres),getResources().getDrawable(R.drawable.sres),"Vegan Resto","Vegan Resto"));



        return binding.getRoot() ;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateRecyclerView(arrayList);

    }
    private void populateRecyclerView(ArrayList<FoodModel> offersArrayList) {
        FoodListAdapter exclusiveRecyclerAdapter = new FoodListAdapter(getActivity(), offersArrayList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getActivity(), orientation, false);
        binding.recycler.setLayoutManager(mLayoutManager);
        binding.recycler.setItemAnimator(new DefaultItemAnimator());
        binding.recycler.setAdapter(exclusiveRecyclerAdapter);

        exclusiveRecyclerAdapter.notifyDataSetChanged();

    }

}
