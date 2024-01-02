package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityProductListBinding;



import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.api.APIClient;
import com.example.myapplication.model.ProductList.ProductListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    ActivityProductListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callApi();

    }

    private void callApi() {
        Call<ProductListResponse> call = APIClient.getInstance().getApi().getProdutData();
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                if (response.isSuccessful()){
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProductListActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    ProductAdapter adapter = new ProductAdapter(ProductListActivity.this , response.body().getProducts());
                    binding.recyclerView.setAdapter(adapter);

                }else {
                    Toast.makeText(ProductListActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}