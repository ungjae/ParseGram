package com.example.parsegram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {


    private RecyclerView rvGrams;
    private GramAdapter adapter;
    private List<Post> posts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvGrams = findViewById(R.id.rvGrams);
        posts = (List<Post>) new ParseQuery<Post>(Post.class);
        adapter = new GramAdapter(this,posts);
        rvGrams.setLayoutManager(new LinearLayoutManager(this));
        rvGrams.setAdapter(adapter);
        adapter.addGrams(posts);


    }
}
