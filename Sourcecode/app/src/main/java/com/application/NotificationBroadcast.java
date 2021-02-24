package com.application;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationBroadcast extends BroadcastReceiver {
    private static String todo;
    @Override
    public void onReceive(Context context, Intent intent) {
        final Intent notificationIntent = new Intent(context,MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notify")
                .setSmallIcon(R.drawable.notify_icon)
                .setContentTitle("Du wolltest noch etwas erledigen:")
                .setContentText(todo)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        managerCompat.notify(200,builder.build());
    }

    public static void setToDoForNotification(String t) {
        todo = t;
    }
}
