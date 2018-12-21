package com.example.user.androidtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.androidtest.model.User;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        RecyclerView recycle = findViewById(R.id.recyclerView);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);

        final ListUserAdapter adapter = new ListUserAdapter();
        recycle.setAdapter(adapter);

        UserListViewModel listViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        listViewModel.getUserData().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(@Nullable PagedList<User> users) {
                adapter.submitList(users);
            }
        });

    }
}
