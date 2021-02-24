package com.application;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is a activity to set a notification for a todoElement. It is called in the ToDoActivity by clicking
 * on the bell in the input view.
 * The user can with a slider choose in how many minutes and hours the smartphone should notify the user about this todoElement
 */
public class NotificationActivity extends AppCompatActivity {
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;
    private TextView todoView;
    private static String input;
    private Slider notificationSliderHours;
    private Slider notificationSliderMinutes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        createNotificationChannel(); // See method down

        // Set the attributes of the different TextViews
        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);
        this.todoView = findViewById(R.id.choosed_todo);

        // Set the date strings (from class DateHandler) in the different TextView attributes and the specific todoElement
        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());
        this.todoView.setText(input);

        this.notificationSliderHours = findViewById(R.id.notification_slider_hours); // Get the slider element for the hours
        // Set a labelFormatter for the hour slider element
        notificationSliderHours.setLabelFormatter(new LabelFormatter() {
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

        this.notificationSliderMinutes = findViewById(R.id.notification_slider2_minutes); // Get the slider element for the minutes
        // Set a labelFormatter for the minute slider element
        notificationSliderMinutes.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                if((int) value == 1) {
                    return ((int) value) + " Minute";
                } else {
                    return ((int) value) + " Minuten";
                }
            }
        });

        Button menuButton = findViewById(R.id.menu_button_todo); // Get the menuButton
        // Set a onClickListener on the menuButton to switch to the activity before by clicking
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to activity before
            }
        });

        ImageView calendar = findViewById(R.id.calendar_background); // Get the calendar view element
        // Set a onClickListener on the calendar view to switch to ChangeDateActivity by clicking on it
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotificationActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });

        Button saveButton = findViewById(R.id.save_button_noti); // Get the save button to save a notification
        // Set a onClickListener on the save button to save the notification by clicking on it
        saveButton.setOnClickListener(v -> {
                int hoursValue = (int) notificationSliderHours.getValue(); // Get the input for the hours from slider
                int minutesValue = (int) notificationSliderMinutes.getValue(); // Get the input for the minutes from slider
                long timeInMillis = 0L; // Create a new helper variable to calculate the notification time

                // Setting the toasts which are pop up if the user click the save button
                if(hoursValue == 0 && minutesValue != 0) {
                    // Only minutes are choosed
                    if(minutesValue == 1) {
                        Toast.makeText(this,"Du wirst in " + minutesValue + " Minute an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,"Du wirst in " + minutesValue + " Minuten an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    }
                    timeInMillis = 60000 * minutesValue;
                } else if(minutesValue == 0 && hoursValue != 0) {
                    // Only hours are choosed
                    if(hoursValue == 0) {
                        Toast.makeText(this,"Du wirst in " + hoursValue +  " Stunde an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,"Du wirst in " + hoursValue +  " Stunden an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    }
                    timeInMillis = 3600000 * hoursValue;
                } else if(hoursValue == 0 && minutesValue == 0) {
                    // Nothing is choosed
                    Toast.makeText(this,"Du musst eine Zeit auswählen, an der du erinnert werden möchtest!",Toast.LENGTH_SHORT).show();
                } else {
                    // Minutes and hours are choosed
                    if(minutesValue == 1 && hoursValue == 1) {
                        Toast.makeText(this,"Du wirst in " + hoursValue +  " Stunde und " + minutesValue + " Minute an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    } else if(minutesValue == 1 && hoursValue > 1) {
                        Toast.makeText(this,"Du wirst in " + hoursValue +  " Stunden und " + minutesValue + " Minute an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    } else if(minutesValue > 1 && hoursValue == 1) {
                        Toast.makeText(this,"Du wirst in " + hoursValue +  " Stunde und " + minutesValue + " Minuten an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,"Du wirst in " + hoursValue +  " Stunden und " + minutesValue + " Minuten an dein ToDo erinnert",Toast.LENGTH_SHORT).show();
                    }
                    timeInMillis = 3600000 * hoursValue + 60000 * minutesValue;
                }

                NotificationBroadcast.setToDoForNotification(input); // Setting the todoElement in the class NotificationBroadcast

                // Create a new notification on the user choosed time
                if(timeInMillis != 0L) {
                    Intent intent = new Intent(NotificationActivity.this, NotificationBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this, 0, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButton = System.currentTimeMillis();
                    alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButton + timeInMillis, pendingIntent);
                }
        });

    }

    // A notification channel is now needed for all notifications on the smartphone. Without it will not work
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= (Build.VERSION_CODES.O)) {
            CharSequence name = "NotificationReminderChannel";
            String description = "Channel for Notification Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Setting the attribute input which contains the specific todoElement in class ToDoActivity
     * @param todo Contains the specific todoElement
     */
    public static void setToDoInput(String todo) {
        input = todo;
    }
}