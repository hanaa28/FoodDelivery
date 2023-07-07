package com.example.fooddelivery.activities.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.databinding.ActivityBioBinding;

public class BioActivity extends AppCompatActivity {
    ActivityBioBinding binding;
    private SharedPrefs sharedPref;
    SharedPreferences.Editor seditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bio);

      //  Fragment profileFragment=new ProfileFragment().newInstance("hanaaa");
//          Bundle bundle=new Bundle();
//        bundle.putString("name",binding.firstname.getText().toString());
//          Fragment pfrag=new ProfileFragment();
//          pfrag.setArguments(bundle);

        sharedPref = new SharedPrefs(getApplicationContext());


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  sharedPref.setSignInInfo(binding.firstname.getText().toString(), binding.lastname.getText().toString(), binding.phonenumber.getText().toString());
                Intent intent1=new Intent(BioActivity.this,photoActivity.class);

                startActivity(intent1);
            }
        });
//        sharedref= PreferenceManager.getDefaultSharedPreferences(this);
//        seditor=sharedref.edit();



    }
}
//    getSupportFragmentManager().beginTransaction().replace(binding.firstname.getId(),
//        profileFragment).commitAllowingStateLoss();
