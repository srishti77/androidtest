package com.example.user.androidtest.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.paging.DataSource;

/**
 * Created by User on 20/12/2018.
 */
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("select * from User")
    DataSource.Factory<Integer, User> getAllUser();


}
