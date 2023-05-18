package com.example.fooddelivery.activities.models;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("otp")
	private int otp;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getMobile(){
		return mobile;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getOtp(){
		return otp;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}