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
import com.example.fooddelivery.activities.interfaces.UserInterface;
import com.example.fooddelivery.activities.models.ErrorResponse;
import com.example.fooddelivery.activities.models.LoginModel;
import com.example.fooddelivery.activities.models.RegisterErrorResponse;
import com.example.fooddelivery.activities.models.User;
import com.example.fooddelivery.activities.models.UserResponse;
import com.example.fooddelivery.activities.utils.RetrofitClient;
import com.example.fooddelivery.databinding.ActivityLoginBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginActivity extends AppCompatActivity {

    static String token;

    public static String getToken() {
        return token;
    }
   Intent intent;
        ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
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
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    UserInterface user = RetrofitClient.getRetrofitInstance().create(UserInterface.class);
                    LoginModel loginModel = new LoginModel(binding.email.getText().toString(), binding.password.getText().toString());
                    Call<UserResponse> call = user.loginUser(loginModel);
                    System.out.println("///////////" + call);
                    call.enqueue(new Callback<UserResponse>() {

                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.body() == null) {
                                Gson gson = new Gson();
                                ErrorResponse ErrorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                                if (ErrorResponse.getMassage() != null) {
                                    Toast.makeText(LoginActivity.this, ErrorResponse.getMassage(), Toast.LENGTH_SHORT).show();
                                }

                                if (binding.email.getText().length()==0){
                                    Toast.makeText(LoginActivity.this,"email is required",Toast.LENGTH_SHORT).show();
                                }
                                if (binding.password.getText().length()==0){
                                    Toast.makeText(LoginActivity.this,"password is required",Toast.LENGTH_SHORT).show();

                                }
                                if (binding.password.getText().length()<6){
                                    Toast.makeText(LoginActivity.this,"invalid password",Toast.LENGTH_SHORT).show();

                                }


                            }
                          else  {
                                token = response.body().getToken();
                                intent = new Intent(LoginActivity.this, MainresActivity.class);
                                startActivity(intent);
                            }

                        }


                        @Override
                        public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {

                        }
                    });


            }
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