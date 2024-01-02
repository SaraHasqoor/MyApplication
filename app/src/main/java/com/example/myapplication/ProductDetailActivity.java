package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;

    String title , desc , price , image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent()!=null){
            image = getIntent().getStringExtra("img");
            title = getIntent().getStringExtra("title");
            desc = getIntent().getStringExtra("desc");
            price = getIntent().getStringExtra("price");

            Glide.with(this).load(image).into(binding.profileImage);
            binding.txtName.setText(title);
            binding.txtDescription.setText(desc);
            binding.txtPrice.setText(String.valueOf(price));



        }else {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }





    }
}