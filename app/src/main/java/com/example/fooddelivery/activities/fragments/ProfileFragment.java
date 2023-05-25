package com.example.fooddelivery.activities.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.Constants;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.databinding.FragmentProfileBinding;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private String namee;
    private String photo;
     static final String Name="name";
     private SharedPrefs sharedPrefs;

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

        if (bundle !=null){
             namee=bundle.getString(Name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View v = binding.getRoot();
//        ImageView imageview = v.findViewById(R.id.viewImage1);
//        CircleImageView imageView1 = v.findViewById(R.id.viewImage);
        sharedPrefs = new SharedPrefs(requireActivity());
        photo = sharedPrefs.getPhoto().get(Constants.KEY_PHOTO);
        System.out.println("************************");
        System.out.println(photo);
        if (photo != null) {
            byte[] b = Base64.decode(photo, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            binding.viewImage.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }


//        image.put
        return v;

    }
}