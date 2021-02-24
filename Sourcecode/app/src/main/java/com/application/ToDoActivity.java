package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * @author neverchange95
 * @version 02/2020
 *
 * This is a activity which shows all todos on a specific choosed date in the MainActivity.
 */
public class ToDoActivity extends AppCompatActivity {
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;
    private static ToDoAdapter adapter;
    private static GridView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        // Set the attributes of the different TextViews
        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        // Set the date strings (from class DateHandler) in the different TextView attributes
        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());

        adapter = new ToDoAdapter(this); // Set the adapter to ToDoAdapter which creates the grid view for the todoElement fields

        layout = findViewById(R.id.grid_todo);
        layout.setAdapter(adapter);

        Button menuButton = findViewById(R.id.menu_button_todo); // Getting the menu button to switch to the activity before
        // Set a onClickListener on the menu button to switch to the activity before by clicking on it
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.refreshMainLayout();
                finish(); // go back to activity before
            }
        });

        Button notificationButton = findViewById(R.id.notifications); // Getting the notification button to switch to the notification activity
        // Set an onClickListener on the notificationButton to switch to the NotificationActivity by clicking on it
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.input_todo);
                NotificationActivity.setToDoInput(input.getText().toString()); // Setting the todoElement into the input attribute in the NotificationActivity
                Intent i = new Intent(ToDoActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });

        ImageView calendar = findViewById(R.id.calendar_background); // Getting the calendar view element
        // Setting a onClickListener on the calendar view for switching to ChangeDateActivity by clicking
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ToDoActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });

        // clear the placeholder if user click on the input field
        final EditText inputTodo = (EditText)findViewById(R.id.input_todo); // Get the input element on which the user writes the task in it
        // Set a onFocusChangeListener on the input element, so if the user click on the input field, the placeholder will be cleared
        inputTodo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    inputTodo.getText().clear();
                }
            }
        });

        Button saveToDoButton = findViewById(R.id.save); // Get the saveToDoButton element
        // Set a onClickListener on the saveToDoButton which triggers saving of the task in the database, if user clicks on it
        saveToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputTodo = (EditText)findViewById(R.id.input_todo);
                String todo = (inputTodo.getText()).toString();
                try {
                    ToDoHandler.setToDoArray(todo,dh.getDay()); // Set the input in the hash map in class ToDoHandler and save it also in database
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // Triggers the refreshing of the layout if a new element would be saved
                adapter.notifyDataSetChanged();
                layout.invalidateViews();
                layout.setAdapter(adapter);
            }
        });
    }

    /**
     * This method triggers the refreshing of the layout and is called in class ToDoAdapter, if the user delete a element
     */
    public static void refreshLayout() {
        adapter.notifyDataSetChanged();
        layout.invalidateViews();
        layout.setAdapter(adapter);
    }
}