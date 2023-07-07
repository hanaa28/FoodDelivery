package com.example.fooddelivery.activities.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.avtivities.CongratulationActivity;
import com.example.fooddelivery.databinding.FragmentNotifcationBinding;

import java.io.ByteArrayOutputStream;

public class notifcationFragment extends Fragment {
FragmentNotifcationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifcation, container, false);

        if (CongratulationActivity.getNoti_key() == "done") {
            binding.correct.setImageDrawable((getResources().getDrawable(R.drawable.correct)));
            binding.txt.setText("Order will arrive soon ...");

        }
        return binding.getRoot();
    }
}