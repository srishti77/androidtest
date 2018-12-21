package com.example.user.androidtest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.user.androidtest.model.User;
import com.example.user.androidtest.model.UserRepository;

/**
 * Created by User on 20/12/2018.
 */

public class UserListViewModel extends AndroidViewModel {

    UserRepository repo;
    private LiveData<PagedList<User>> userData;

    public UserListViewModel(@NonNull Application context){
        super(context);
        repo = UserRepository.getInstance(context);
        PagedList.Config pageList = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(50)
                .build();

        DataSource.Factory<Integer, User>  dataSource = repo.getAllUser();
        userData = new LivePagedListBuilder<Integer, User>(dataSource, pageList).build();
    }

    public LiveData<PagedList<User>> getUserData(){
        return userData;
    }

    public void insert(User user){
        repo.insert(user);
    }


}
