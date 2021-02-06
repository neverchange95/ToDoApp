package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        GridView layout = findViewById(R.id.grid_todo);
        layout.setAdapter(new ToDoAdapter(this));

        Button menuButton = findViewById(R.id.menu_button_todo);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // go back to overview (MainActivity)
            }
        });

        Button notificationButton = findViewById(R.id.notifications);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ToDoActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });
    }
}