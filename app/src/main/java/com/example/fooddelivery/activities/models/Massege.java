package com.example.fooddelivery.activities.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Massege{

	@SerializedName("password")
	private List<String> password;

	@SerializedName("email")
	private List<String> email;

	public List<String> getPassword(){
		return password;
	}

	public List<String> getEmail(){
		return email;
	}
}