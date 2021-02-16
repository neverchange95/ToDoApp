package com.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChangeDateActivity extends AppCompatActivity {
    private DateHandler dateHandler = DateHandler.getInstance();
    private String choosedDay;
    private String choosedMonth;
    private String choosedYear;
    CalendarView cv;
    DateHandler dh = DateHandler.getInstance();
    TextView day2;
    TextView month2;
    TextView year2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_date);

        this.day2 = findViewById(R.id.day);
        this.month2 = findViewById(R.id.month);
        this.year2 = findViewById(R.id.year);

        day2.setText(dh.getDay());
        month2.setText(dh.getMonth());
        year2.setText(dh.getYear());



        cv = (CalendarView) findViewById(R.id.calendarView);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat d2 = new SimpleDateFormat("EEEE"); // set a format to only get the actual day in words
                Calendar calendar = Calendar.getInstance(); // Create a new Calendar, to get the correct choosed day
                calendar.set(Calendar.YEAR,year); // Set the year
                calendar.set(Calendar.MONTH,month); // Set the month
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth); // Set the year

                choosedDay = DateHandler.getDayFormatted(d2.format(calendar.getTime())); // Get the choosed day in the format: Monday, Tuesday... and change them in DateHandler to Mo. 01, Di. 02, ...

                if(dayOfMonth < 10) {
                    choosedDay = choosedDay + "0" + dayOfMonth;
                } else {
                    choosedDay = choosedDay + dayOfMonth+"";
                }
                choosedMonth = DateHandler.getMonthInWords(month+1+""); // month is beginning by 0 (Jan = 0) and change them in DateHandler to Januar, Februar, ...
                choosedYear = year+"";

                day2.setText(choosedDay);
                month2.setText(choosedMonth);
                year2.setText(choosedYear);
            }
        });


        Button menuButton = findViewById(R.id.menu_button_todo);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to overview (MainActivity)
            }
        });

        Button todoButton = findViewById(R.id.button_to_todo);
        todoButton.setOnClickListener(new View.OnClickListener() {
            // TODO: Wenn ein Datum aus dem aktuellen Monat aufgerufen wird, muss auf die ArrayList aus der HashMap dieses Monats zurück gegriffen werden und nicht das calendarChoosedTodos aufgerufen werden!
            @Override
            public void onClick(View v) {
                // Problem: aktuelles Jahr und Monat muss behalten werden. Swicht der user zurück auf die main, bleibt das gewählte jahr und monat gesetzt.
//                dateHandler.setDay(choosedDay);
//                dateHandler.setMonth(choosedMonth);
//                dateHandler.setYear(choosedYear);
                ToDoHandler handler = new ToDoHandler("test");
                ToDoAdapter.setToDoAdapterArray(handler.getCalendarChoosedTodos());
                Intent i = new Intent(ChangeDateActivity.this, ToDoActivity.class);
                startActivity(i);
            }
        });
    }
}