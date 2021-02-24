package com.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is a activity to change the date and to show the user some old todos.
 * The user can click on the calendar view in all activities and will moved to this activity.
 * In this activity the user will shown a calendar where he can choose a specific date.
 * By clicking on the button with a hook in the picture the user will moved to the ShowPastToToActivity
 * where he can see all todos from the choosed date.
 */
public class ChangeDateActivity extends AppCompatActivity {
    private String choosedDay;
    private String choosedMonth;
    private String choosedYear;
    private CalendarView cv; // Save the calendar view, where the user can change the date
    private ActualDate actualDate = DateHandler.getActualDate(); // Getting the actual date
    private TextView day2;
    private TextView month2;
    private TextView year2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_date);

        // Set the attributes of the different TextViews
        this.day2 = findViewById(R.id.day);
        this.month2 = findViewById(R.id.month);
        this.year2 = findViewById(R.id.year);

        // Set the date strings (from class ActualDate) in the different TextView attributes
        day2.setText(actualDate.getDay());
        month2.setText(actualDate.getMonth());
        year2.setText(actualDate.getYear());

        cv = (CalendarView) findViewById(R.id.calendarView); // Get the calender view to set a onClickListener on it (it is the calendar where the user can choose a specific date)
        // Setting the onClickListener for the calendar view to get the specific choosed date
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat d2 = new SimpleDateFormat("EEEE"); // set a format to only get the actual day in words
                Calendar calendar = Calendar.getInstance(); // Create a new Calendar, to get the correct choosed day
                calendar.set(Calendar.YEAR,year); // Set the year
                calendar.set(Calendar.MONTH,month); // Set the month
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth); // Set the year

                choosedDay = DateHandler.getDayFormatted(d2.format(calendar.getTime())); // Get the choosed day in the format: Monday, Tuesday... and change them in DateHandler to Mo. 01, Di. 02, ...

                // Setting here the correct format for the specific days, to has the same format with the database contents
                if(dayOfMonth < 10) {
                    choosedDay = choosedDay + "0" + dayOfMonth;
                } else {
                    choosedDay = choosedDay + dayOfMonth+"";
                }
                choosedMonth = DateHandler.getMonthInWords(month+1+""); // month is beginning by 0 (Jan = 0) and change them in DateHandler to Januar, Februar, ...
                choosedYear = year+""; // Move the choosed year from a int value to a string value

                // Set here the actual choosed date strings in the different TextView attributes
                day2.setText(choosedDay);
                month2.setText(choosedMonth);
                year2.setText(choosedYear);
            }
        });

        Button menuButton = findViewById(R.id.menu_button_todo); // Get the menuButton where the user can switch to the activity before
        // Setting the onClickListener for the menuButton to give them functionality
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to activity before
            }
        });

        Button todoButton = findViewById(R.id.button_to_todo); // Get the todoButton to move to the ShowPastToDoActivity
        // Setting the onClickListener for the todoButton to switch from this activity to the ShowPastToDoActivity, where the user can see all past todos for the choosed date
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PastToDoHandler handler = null; // Creating a new PastToDoHandler to get all todos for the choosed date from database
                try {
                    handler = new PastToDoHandler(day2.getText().toString() + "." + month2.getText().toString() + "." + year2.getText().toString()); // Setting the handler with the specific choosed date
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                PastToDoAdapter.setToDoAdapterArray(handler.getCalendarChoosedTodos()); // Setting the array in the PastToDoAdapter with the returned todos from the handler
                Intent i = new Intent(ChangeDateActivity.this, ShowPastToDoActivity.class);
                ShowPastToDoActivity.ChoosedDateClass t = new ShowPastToDoActivity.ChoosedDateClass(choosedDay,choosedMonth,choosedYear); // Setting the static inner class in ShowPastToDoActivity
                startActivity(i);
            }
        });
    }
}