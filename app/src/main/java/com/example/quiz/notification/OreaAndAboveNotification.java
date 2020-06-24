package com.example.quiz.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.example.quiz.R;

public class OreaAndAboveNotification extends ContextWrapper {
    private static final String id = "some_id";
    private static final String NAME = "DreamIIT";

    private NotificationManager notificationManagerCompat;

    public OreaAndAboveNotification(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        NotificationChannel notificationChannel = new NotificationChannel(id,NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(notificationChannel);


    }
    public NotificationManager getManager(){
        if(notificationManagerCompat == null){
            notificationManagerCompat = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return notificationManagerCompat;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getONotification(String title, String body, PendingIntent pintent, Uri soundUri, String icon ){
        return new Notification.Builder(getApplicationContext(), id)
                .setContentIntent(pintent)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.mohitpic);
    }

}
