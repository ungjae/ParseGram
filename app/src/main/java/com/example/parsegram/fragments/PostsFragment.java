package com.example.parsegram.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parsegram.Post;
import com.example.parsegram.PostsAdapter;
import com.example.parsegram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    private SwipeRefreshLayout swipeContainer;

    public static final String TAG = "PostsFragment";
    protected RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> mPosts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);

        mPosts = new ArrayList<>();
        //create adapter
        adapter = new PostsAdapter(getContext(), mPosts);
        //create data source
        //set adapter on recycler view
        rvPosts.setAdapter(adapter);
        //set layoutmanager on recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeColors(getResources().getColor(android.R.color.holo_orange_dark));
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("PostsFragment","setOnRefreshListener");
                adapter.clear();
                queryPosts();
                swipeContainer.setRefreshing(false);

            }
        });

    }

    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override

            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }

                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for(int i = 0; i < posts.size(); i++)
                    Log.d(TAG, "post: " + posts.get(i).getDescription() + "username" + posts.get(i).getUser().getUsername());
            }
        });
    }
}
