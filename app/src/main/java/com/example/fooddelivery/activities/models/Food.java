package com.example.fooddelivery.activities.models;

import com.google.gson.annotations.SerializedName;

public class Food {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private String price;

	@SerializedName("restaurent_id")
	private String restaurentId;

	@SerializedName("name")
	private String name;

	@SerializedName("cat_id")
	private String catId;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("pic")
	private String pic;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPrice(){
		return price;
	}

	public String getRestaurentId(){
		return restaurentId;
	}

	public String getName(){
		return name;
	}

	public String getCatId(){
		return catId;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getPic(){
		return pic;
	}
}