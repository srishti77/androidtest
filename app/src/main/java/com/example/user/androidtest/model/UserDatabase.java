package com.example.user.androidtest.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by User on 20/12/2018.
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public static UserDatabase instance = null;
    public abstract UserDao userDao();
    public static synchronized UserDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback(){
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    insertInitialElement(context);
                                }
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }




    public static void insertInitialElement(Context context){
        UserDao userDAO = getInstance(context).userDao();
        userDAO.insert(new User("Srishti", "Germany"));
        userDAO.insert(new User("Srishti", "UK"));
        userDAO.insert(new User("Srishti", "USA"));

    }

}
