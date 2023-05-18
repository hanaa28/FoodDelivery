package com.example.fooddelivery.activities.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("token")
    private String token;
    @SerializedName("status")
    private String status;

    @SerializedName("user")
    private User user;

    public String getToken() {
        return token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
