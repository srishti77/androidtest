package com.example.user.androidtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by User on 21/12/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJob extends JobService {
    public static final String CHANNEL_ID = "notify";
    public static final int Notification_Id = 18;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
            .setContentTitle("Title")
                .setContentText("Text goes here")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(Notification_Id, builder.build());

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
