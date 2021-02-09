package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDateActivity extends AppCompatActivity {
    public CalendarView cv;
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_date);

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());


        cv = (CalendarView) findViewById(R.id.calendarView);

    //    DateHandler dh = new DateHandler(cv);


        Button menuButton = findViewById(R.id.menu_button_todo);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to overview (MainActivity)
            }
        });
    }
}