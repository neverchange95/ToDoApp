package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is a activity which shows all past todos on a specific choosed date in the ChangeDateActivity
 */
public class ShowPastToDoActivity extends AppCompatActivity {
    private TextView day;
    private TextView month;
    private TextView year;
    private static PastToDoAdapter adapter;
    private static GridView layout;

    /**
     * Setting here a static inner class which handles the choosed date from user.
     * This class is is called to set the attributes in the ChangeDateActivity and to get the attributes
     * in the outer class here (ShowPastToDoActivity).
     */
    public static class ChoosedDateClass {
        private static String day;
        private static String month;
        private static String year;

        /**
         * Constructor to set the day attributes. Called in ChangeDateActivity
         * @param d Contains the choosed day
         * @param m Contains the choosed month
         * @param y Contains the choosed year
         */
        public ChoosedDateClass(String d, String m, String y) {
            day = d;
            month = m;
            year = y;
        }

        /**
         * @return The choosed day
         */
        public static String getDay() {
            return day;
        }

        /**
         * @return The choosed month
         */
        public static String getMonth() {
            return month;
        }

        /**
         * @return The choosed year
         */
        public static String getYear() {
            return year;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_past_to_do);

        // Set the attributes of the different TextViews
        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        // Get the choosed day elements from the inner class ChoosedDateClass and set them to the attributes of this outer class
        String choosedDay = ChoosedDateClass.getDay();
        String choosedMonth = ChoosedDateClass.getMonth();
        String choosedYear = ChoosedDateClass.getYear();

        if(choosedDay==null && choosedMonth==null && choosedYear==null) {
            // If the user has not choosed a date and switch to this activity, the attributes must have the values of the actual day
            this.day.setText(DateHandler.getActualDate().getDay());
            this.month.setText(DateHandler.getActualDate().getMonth());
            this.year.setText(DateHandler.getActualDate().getYear());
        } else {
            // The user has choosed a date, so set the attributes to this values
            this.day.setText(ChoosedDateClass.getDay());
            this.month.setText(ChoosedDateClass.getMonth());
            this.year.setText(ChoosedDateClass.getYear());
        }

        adapter = new PastToDoAdapter(this);  // Set the adapter to PastToDoAdapter which creates the grid view for the past todos

        layout = findViewById(R.id.grid_past_todo);
        layout.setAdapter(adapter);

        Button menuButton = findViewById(R.id.menu_button_todo); // Get the menu button to switch to the activity before by clicking on it
        // Set a onClickListener on the menu button to give them the functionality
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to the activity before
            }
        });
    }
}