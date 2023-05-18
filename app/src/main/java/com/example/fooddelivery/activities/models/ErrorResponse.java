package com.example.fooddelivery.activities.models;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("massage")
    private String massage;
    @SerializedName("status")
    private boolean status;
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


}
