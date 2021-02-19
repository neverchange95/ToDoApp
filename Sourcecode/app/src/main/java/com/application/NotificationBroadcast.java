package com.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyLemubit")
                .setSmallIcon(R.drawable.delete)
                .setContentTitle("Remind Lemubit Academy")
                .setContentText("Hey ho, this is a soft reminder...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        managerCompat.notify(200,builder.build());
    }
}
