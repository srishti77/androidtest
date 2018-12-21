package com.example.user.androidtest;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.androidtest.model.User;
import com.example.user.androidtest.model.UserRepository;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText name, address;
    Button add;
    UserRepository repo;
    UserListViewModel model;
    public static final int Job_id = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.edName);
        address = (EditText) findViewById(R.id.edAddress);
        add = (Button) findViewById(R.id.add);
        repo = UserRepository.getInstance(getApplication());

        final UserListViewModel listViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(name.getText().toString(),address.getText().toString());
                listViewModel.insert(user);
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.notify:
                JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                ComponentName comp = new ComponentName(this, NotificationJob.class);
                JobInfo.Builder info = new JobInfo.Builder(Job_id, comp);
                info.setMinimumLatency(TimeUnit.DAYS.toMillis(1));

                jobScheduler.schedule(info.build());

        }
        return super.onOptionsItemSelected(item);
    }
}
