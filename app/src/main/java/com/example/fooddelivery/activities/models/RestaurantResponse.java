package com.example.fooddelivery.activities.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RestaurantResponse{

	@SerializedName("data")
	private List<Restaurant> data;

	@SerializedName("status")
	private boolean status;

	RestaurantResponse(boolean status, List<Restaurant> data) {
		this.data = data;
		this.status = status;
	}

	public List<Restaurant> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}