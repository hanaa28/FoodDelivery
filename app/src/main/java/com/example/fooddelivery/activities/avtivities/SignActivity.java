package com.example.fooddelivery.activities.avtivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.interfaces.UserInterface;
import com.example.fooddelivery.activities.models.RegisterErrorResponse;
import com.example.fooddelivery.activities.models.RegisterModel;
import com.example.fooddelivery.activities.models.UserResponse;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.ActivitySignBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignActivity extends AppCompatActivity {
    ActivitySignBinding binding;
    static String token;
    public static String getToken() {
        return token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign);
        binding.phoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        binding.name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        binding.email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        binding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        binding.haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.createAccount.setOnClickListener(v -> {
            UserInterface user = RetrofitClient.getRetrofitInstance().create(UserInterface.class);
            RegisterModel registerModel = new RegisterModel(
                    binding.email.getText().toString(),
                    binding.password.getText().toString(),
                    binding.phoneNum.getText().toString(),
                    binding.name.getText().toString());
            String name= binding.name.getText().toString();
            String phone=binding.phoneNum.getText().toString();

            SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
            sharedPrefs.setSignInInfo(name,phone);
            Call<UserResponse> call = user.registerUser(registerModel);
            call.enqueue(new Callback<UserResponse>() {

                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.body() == null) {
                        Gson gson = new Gson();
                        RegisterErrorResponse registerErrorResponse = gson.fromJson(response.errorBody().charStream(), RegisterErrorResponse.class);
                        if (registerErrorResponse.getMassege().getEmail() != null) {
                            Toast.makeText(SignActivity.this, registerErrorResponse.getMassege().getEmail().get(0), Toast.LENGTH_SHORT).show();

                        }
                        if (registerErrorResponse.getMassege().getPassword() != null) {
                            Toast.makeText(SignActivity.this, registerErrorResponse.getMassege().getPassword().get(0), Toast.LENGTH_SHORT).show();

                        }
                        if (binding.name.getText().length()==0){
                            Toast.makeText(SignActivity.this,"name is required",Toast.LENGTH_SHORT).show();
                        }
                        if (binding.phoneNum.getText().length()==0){
                            Toast.makeText(SignActivity.this,"phone is required",Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        token = response.body().getToken();
                        Intent intent1 = new Intent(SignActivity.this,LocationActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {

                }
            });

        });


    }

    public void showHidePass(View view) {

        if (view.getId() == R.id.show_pass_btn) {
            if (binding.password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_show_password);
                //Show Password
                binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_hide_password);
                //Hide Password
                binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}