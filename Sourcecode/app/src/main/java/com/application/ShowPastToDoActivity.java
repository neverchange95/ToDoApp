package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ShowPastToDoActivity extends AppCompatActivity {
    private TextView day;
    private TextView month;
    private TextView year;
    private static PastToDoAdapter adapter;
    private static GridView layout;

    public static class ChoosedDateClass {
        private static String day;
        private static String month;
        private static String year;
        public ChoosedDateClass(String d, String m, String y) {
            day = d;
            month = m;
            year = y;
        }
        public static String getDay() {
            return day;
        }
        public static String getMonth() {
            return month;
        }
        public static String getYear() {
            return year;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_past_to_do);

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        this.day.setText(ChoosedDateClass.getDay());
        this.month.setText(ChoosedDateClass.getMonth());
        this.year.setText(ChoosedDateClass.getYear());

        adapter = new PastToDoAdapter(this);


        layout = findViewById(R.id.grid_past_todo);
        layout.setAdapter(adapter);


        Button menuButton = findViewById(R.id.menu_button_todo);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to overview (MainActivity)
            }
        });


    }
}