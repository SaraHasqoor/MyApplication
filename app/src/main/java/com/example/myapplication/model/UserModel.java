package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserModel{

	@SerializedName("UserModel")
	private List<UserModelItem> userModel;

	public void setUserModel(List<UserModelItem> userModel){
		this.userModel = userModel;
	}

	public List<UserModelItem> getUserModel(){
		return userModel;
	}
}