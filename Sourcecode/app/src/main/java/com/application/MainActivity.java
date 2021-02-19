package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.TestLooperManager;
import android.provider.Settings;
import android.view.View;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static String MyPREFERENCES = "MyPREFERENCES";
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());


        String[] days = dh.getAllDaysFormatted(); // get array with all days of the current month formatted like Mo. 01 from class DateHandler
        GridView layout = findViewById(R.id.grid);
        layout.setAdapter(new DayAdapter(this, days,dh.getDay()));


        ImageView calendar = findViewById(R.id.calendar_background);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });
    }
}