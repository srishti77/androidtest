package com.example.user.androidtest.model;

import android.app.Application;
import android.arch.paging.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by User on 20/12/2018.
 */

public class UserRepository {
    public static UserDatabase userDatabase;
    private DataSource.Factory<Integer, User> pagedUser;
    UserDao userDao;
    public static  volatile UserRepository repo;
    private final ExecutorService mIoExecutor;

    public static UserRepository getInstance(Application application){
        if(repo  == null){
            userDatabase = UserDatabase.getInstance(application);
            repo = new UserRepository(userDatabase.userDao(),
                    Executors.newSingleThreadExecutor());
        }
        return repo;
    }

    public UserRepository(UserDao dao, ExecutorService executor) {
        mIoExecutor = executor;
        userDao = dao;
    }

    public void insert(final User user){
        mIoExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });
    }

    public DataSource.Factory<Integer, User> getAllUser(){
        return userDao.getAllUser();
    }

}
