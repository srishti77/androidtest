package com.example.user.androidtest.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by User on 20/12/2018.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;

    String name;
    String address;

    public User(String name, String address)
    {
        this.name=name;
        this.address = address;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
