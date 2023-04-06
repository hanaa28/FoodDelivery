package com.example.fooddelivery.activities.pojo;

import android.graphics.drawable.Drawable;
import android.view.View;

public class FoodModel {
    Drawable image1;
    Drawable image2;
    String RESName1;
    String RESName2;
    int id;

    public FoodModel(int id, Drawable image1, Drawable image2, String RESName1,String RESName2) {
        this.image1 = image1;
        this.image2 = image2;
        this.RESName1 = RESName1;
        this.RESName2=RESName2;
        this.id=id;
    }

    public Drawable getImage1() {
        return image1;
    }

    public void setImage1(Drawable image1) {
        this.image1 = image1;
    }

    public Drawable getImage2() {
        return image2;
    }

    public void setImage2(Drawable image2) {
        this.image2 = image2;
    }

    public String getRESName1() {
        return RESName1;
    }

    public void setRESName1(String RESName1) {
        this.RESName1 = RESName1;
    }
    public String getRESName2() {
        return RESName2;
    }

    public void setRESName2(String RESName2) {
        this.RESName2 = RESName2;
    }
}
