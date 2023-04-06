package com.example.fooddelivery.activities.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.Constants;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.View.FoodViewModel;
import com.example.fooddelivery.activities.config;
import com.example.fooddelivery.databinding.FragmentProfileBinding;

import java.util.HashMap;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private String namee;
    private HashMap<String, String> sharedPrefs;
     static final String Name="name";

    public ProfileFragment() {

    }

    public Fragment newInstance(String name){
        Fragment fragment = new ProfileFragment();
        Bundle bundle= new Bundle();
        bundle.putString(Name, name);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getArguments();
        sharedPrefs = new SharedPrefs(requireActivity()).getSessionInfo();
        System.out.println(sharedPrefs.get(Constants.KEY_FIRSTNAME));
        if (bundle !=null){
             namee=bundle.getString(Name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPrefs = new SharedPrefs(requireActivity()).getSessionInfo();
        String namePref = sharedPrefs.get(Constants.KEY_FIRSTNAME) + " " + sharedPrefs.get(Constants.KEY_LASTNAME);
        TextView name=v.findViewById(R.id.name);
        TextView phone=v.findViewById(R.id.phone);
        name.setText(namePref);
        phone.setText(sharedPrefs.get(Constants.KEY_PHONE));
        return v;

    }
}