package com.example.fooddelivery.activities.models;

public class RegisterModel {
    private String email, password, mobile, name;

    public RegisterModel(String email, String password, String mobile, String name) {
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
