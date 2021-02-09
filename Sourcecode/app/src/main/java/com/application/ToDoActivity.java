package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ToDoActivity extends AppCompatActivity {
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());


        final ToDoAdapter adapter = new ToDoAdapter(this);


        final GridView layout = findViewById(R.id.grid_todo);
        layout.setAdapter(adapter);

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

        ImageView calendar = findViewById(R.id.calendar_background);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ToDoActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });

        // Save a todo
        Button saveToDoButton = findViewById(R.id.save);
        saveToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputTodo = (EditText)findViewById(R.id.input_todo);
                String todo = (inputTodo.getText()).toString();
                ToDoHandler.setToDoArray(todo,dh.getDay());
                adapter.notifyDataSetChanged();
                layout.invalidateViews();
            }
        });
    }
}