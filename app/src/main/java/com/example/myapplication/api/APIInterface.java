package com.example.myapplication.api;

import com.example.myapplication.model.UserModel;
import com.example.myapplication.model.UserModelItem;
import com.example.myapplication.model.ProductList.ProductListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("posts")
    Call<List<UserModelItem>> getUserData();

    @GET("products")
    Call<ProductListResponse> getProdutData();
}
