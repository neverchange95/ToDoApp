package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    private DateHolder holder = new DateHolder();
    private DateHandler dh;
    private TextView day;
    private TextView month;
    private TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        this.dh = holder.getDateHandler();
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

    }
}