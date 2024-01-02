package com.example.myapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ProductDetailActivity;
import com.example.myapplication.model.ProductList.ProductsItem;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.holder> {

    Context context;
    List<ProductsItem> productsItems;

    public ProductAdapter(Context context, List<ProductsItem> productsItems) {
        this.context = context;
        this.productsItems = productsItems;
    }

    @NonNull
    @Override
    public ProductAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product_list , null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.holder holder, int position) {

        holder.txtname.setText(productsItems.get(position).getTitle());
        holder.txtdesc.setText(productsItems.get(position).getDescription());

        Glide.with(context).load(productsItems.get(position).getThumbnail()).into(holder.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context , ProductDetailActivity.class)
                        .putExtra("img" , productsItems.get(position).getThumbnail())
                        .putExtra("title" , productsItems.get(position).getTitle())
                        .putExtra("desc" , productsItems.get(position).getDescription())
                        .putExtra("price" , productsItems.get(position).getPrice()+"")
                );


            }
        });

    }

    @Override
    public int getItemCount() {
        return productsItems.size();
    }

    public class holder extends RecyclerView.ViewHolder{

        TextView txtname , txtdesc;
        CircleImageView profileImage;
        public holder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.txt_name);
            txtdesc = itemView.findViewById(R.id.txt_desc);
            profileImage = itemView.findViewById(R.id.profile_image);

        }
    }
}
