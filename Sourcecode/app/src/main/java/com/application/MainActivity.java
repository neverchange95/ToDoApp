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

        String[] days = {"Mo. 01", "Di. 02", "Mi. 03", "Do. 05", "Fr. 06", "Sa. 07", "So. 08", "Mo. 09", "Di. 10", "Mi. 11", "Do. 12", "Fr. 13", "Sa. 14", "So. 15", "Mo. 16", "Di. 17", "Mi. 18", "Do. 19", "Fr. 20", "Sa. 21", "So. 22", "Mo. 23", "Di. 24", "Mi. 25", "Do. 26", "Fr. 27", "Sa. 28", "So. 29", "Mo. 30", "Di. 31"};
        GridView layout = findViewById(R.id.grid);
        layout.setAdapter(new DayAdapter(this, days));

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        this.dh = holder.getDateHandler();
        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());


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