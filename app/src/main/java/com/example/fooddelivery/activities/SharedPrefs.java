package com.example.fooddelivery.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class SharedPrefs {
    public static final String PREFS_Logger = "LoggerFile";
    int PRIVATE_MODE = 0;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;

    public SharedPrefs(@NonNull Context context) {
        pref = context.getSharedPreferences(PREFS_Logger, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setSignInInfo(String firstname, String phone) {
        editor.putString(Constants.KEY_FIRSTNAME, firstname);
        editor.putString(Constants.KEY_PHONE, phone);
        editor.commit();
    }

public void setLocation(String location){
        editor.putString(Constants.KEY_LOCATION,location);
        editor.commit();
}
    public HashMap<String, String> getlocation() {
        HashMap<String, String> location = new HashMap<>();
        location.put(Constants.KEY_LOCATION, pref.getString(Constants.KEY_LOCATION, null));

        return location;
    }
    public void setOrder(String food, int x) {
        HashMap<String, String> order = new HashMap<>();
        String foods = pref.getString(Constants.KEY_FOOD, null);
        System.out.println(foods + "dd______________________");
        if (foods != null) {
            String[] curr_foods = foods.substring(1, foods.length() - 1).split(",");
            for (String s : curr_foods) {
                String[] temp = s.split("=");
                order.put(temp[0].trim(), temp[1]);
            }
        }
        if (order.containsKey(food) && x != 0) {
            order.replace(food, String.valueOf(Integer.parseInt(Objects.requireNonNull(order.get(food))) + x));
        } else if (order.containsKey(food) && x == 0) {
            order.remove(food);
        } else {
            order.put(food, "1");
        }
        System.out.println(order + "++++++++++++++++++++++++++++++++++++");
        if (order.isEmpty()) {
            editor.putString(Constants.KEY_FOOD, null);
        }
        else {
            editor.putString(Constants.KEY_FOOD, order.toString());
        }
        editor.commit();
    }

    public HashMap<String, String> getOrder() {
        HashMap<String, String> order = new HashMap<>();
        order.put(Constants.KEY_FOOD, pref.getString(Constants.KEY_FOOD, null));

        return order;
    }

    public void setProfilePhoto(String photo) {
        editor.putString(Constants.KEY_PHOTO, photo);
        editor.commit();
    }

    public HashMap<String, String> getPhoto() {
        HashMap<String, String> photo = new HashMap<>();
        photo.put(Constants.KEY_PHOTO, pref.getString(Constants.KEY_PHOTO, null));

        return photo;
    }

    public HashMap<String, String> getSessionInfo() {
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put(Constants.KEY_FIRSTNAME, pref.getString(Constants.KEY_FIRSTNAME, null));
        userInfo.put(Constants.KEY_LASTNAME, pref.getString(Constants.KEY_LASTNAME, null));
        userInfo.put(Constants.KEY_PHONE, pref.getString(Constants.KEY_PHONE, null));

        return userInfo;
    }
}


