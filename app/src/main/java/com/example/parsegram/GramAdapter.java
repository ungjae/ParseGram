package com.example.parsegram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GramAdapter extends RecyclerView.Adapter<GramAdapter.ViewHolder> {



    private Context context;
    private List<Post> posts;

    public GramAdapter (Context context, List<Post> posts){
            this.context = context;
            this.posts = posts;
            }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_post,viewGroup,false);
            return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Post post = posts.get(i);
            viewHolder.etTitle.setText(post.getDescription());
            viewHolder.etHandle.setText(post.getParseUser(post.KEY_USER).toString());
    }

    @Override
    public int getItemCount() {
            return posts.size();
    }

    public void clear(){
            posts.clear();
            notifyDataSetChanged();
    }

    public void addGrams(List<Post> posts){
            posts.addAll(posts);
            notifyDataSetChanged();
    }

    //Pass in context and list of tweets

    // for each row, inflate the layout

    // bind values based on the position of the element

    // define the viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivGram;
        public EditText etTitle;
        public EditText etHandle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGram = itemView.findViewById(R.id.ivGram);
            etHandle = itemView.findViewById(R.id.etHandle);
            etTitle = itemView.findViewById(R.id.etTitle);
        }
    }
}
