package com.example.myapplication;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {

    List<Article>articlelist;
    NewsRecyclerAdapter(List<Article> articleList){
        this.articlelist = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recycler_row,parent,false);
        return new NewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articlelist.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.SourceTextView.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.baseline_broken_image_24)
                .placeholder(R.drawable.baseline_broken_image_24)
                .into(holder.imageView);

    }

    void updateDate(List<Article> data){
   articlelist.clear();;
   articlelist.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articlelist.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,SourceTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.article_title);
            SourceTextView = itemView.findViewById(R.id.article_source);
            imageView = itemView.findViewById(R.id.article_image_view);



        }
    }
}
