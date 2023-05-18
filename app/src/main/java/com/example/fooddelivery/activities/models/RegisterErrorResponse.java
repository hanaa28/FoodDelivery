package com.example.fooddelivery.activities.models;

import com.google.gson.annotations.SerializedName;

public class RegisterErrorResponse{

	@SerializedName("massege")
	private Massege massege;

	@SerializedName("status")
	private boolean status;

	public Massege getMassege(){
		return massege;
	}

	public boolean isStatus(){
		return status;
	}
}