package com.example.fooddelivery.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class SharedPrefs {
    public static final String PREFS_Logger = "LoggerFile";
    int PRIVATE_MODE = 0;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;

    public SharedPrefs(@NonNull Context context) {
        pref = context.getSharedPreferences(PREFS_Logger, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setSignInInfo(String firstname, String lastname, String phone) {
        editor.putString(Constants.KEY_FIRSTNAME, firstname);
        editor.putString(Constants.KEY_LASTNAME, lastname);
        editor.putString(Constants.KEY_PHONE, phone);
        editor.commit();
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


