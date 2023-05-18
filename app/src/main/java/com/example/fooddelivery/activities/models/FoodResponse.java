package com.example.fooddelivery.activities.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FoodResponse{

	@SerializedName("data")
	private List<Food> data;

	@SerializedName("status")
	private boolean status;

	public List<Food> getData(){
		return data;
	}
	public void setData(List<Food> data){
		this.data = data;
	}

	public boolean isStatus(){
		return status;
	}
}