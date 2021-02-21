package com.application;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import java.text.NumberFormat;
import java.util.Currency;

public class NotificationActivity extends AppCompatActivity {
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;
    private TextView todoView;
    private static String input;
    private Slider notificationSlider;
    private Context context = this;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        createNotificationChannel();

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);
        this.todoView = findViewById(R.id.choosed_todo);
        this.todoView.setText(input);

        this.notificationSlider = findViewById(R.id.notification_slider);
        notificationSlider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                if((int) value == 1) {
                    return ((int) value) + " Stunde";
                } else {
                    return ((int) value) + " Stunden";
                }
            }
        });

        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());

        Button menuButton = findViewById(R.id.menu_button_todo);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to overview (MainActivity)
            }
        });


        ImageView calendar = findViewById(R.id.calendar_background);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotificationActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"lemubitA")
//                .setSmallIcon(R.drawable.delete)
//                .setContentTitle("This is a test notification")
//                .setContentText("blablabla hier ist eine benchritigung!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        Button saveButton = findViewById(R.id.save_button_noti);
        saveButton.setOnClickListener(v -> {
                float value = notificationSlider.getValue();
            //    notificationManagerCompat.notify(100,builder.build());
                Toast.makeText(this,"Notification Set!",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NotificationActivity.this,NotificationBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this,0,intent,0);

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                long timeAtButton = System.currentTimeMillis();

                long tenSecInMilli = 1000 * 6;

                alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButton + tenSecInMilli,pendingIntent);
        });

    }

    // lemubitA
    // THIS IS NEEDED FOR NOTIFICATION ON SMARTPHONE!!
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= (Build.VERSION_CODES.O)) {
            CharSequence name = "LemubitReminderChannel";
            String description = "Channel for Lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // This Method is called in ToDoActivity
    public static void setToDoInput(String todo) {
        input = todo;
    }
}