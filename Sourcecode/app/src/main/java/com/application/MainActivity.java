package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.TestLooperManager;
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
    private DateHolder holder = new DateHolder();
    private DateHandler dh;
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

        this.dh = holder.getDateHandler();
        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());


        String[] days = dh.getAllDaysFormatted(); // get array with all days of the current month formatted like Mo. 01 from class DateHandler
        GridView layout = findViewById(R.id.grid);
        layout.setAdapter(new DayAdapter(this, days));


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