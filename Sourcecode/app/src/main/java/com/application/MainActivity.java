package com.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.SQLException;


/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is the MainActivity which is shown as the app is started
 */
public class MainActivity extends AppCompatActivity {
    private DateHandler dh = DateHandler.getInstance();
    private TextView day;
    private TextView month;
    private TextView year;
    private static DayAdapter adapter;
    private static GridView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        // Create a new connection to database by calling the class MySQLConnection()
        try {
            Toast.makeText(this,"Datenbankverbindung wird aufgebaut ...",Toast.LENGTH_SHORT).show();
            new MySQLConnection();
            Toast.makeText(this,"Verbindung zur Datenbank war erfolgreich!",Toast.LENGTH_SHORT).show();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Set the attributes of the different TextViews
        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        // Set the date strings (from class DateHandler) in the different TextView attributes
        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());

        String[] days = dh.getAllDaysFormatted(); // get array with all days of the current month formatted like Mo. 01 from class DateHandler

        // Create a new GridView for all days in a month. Class DayAdapter is handling this
        try {
            adapter = new DayAdapter(this,days,dh.getDay()); // Set the adapter to DayAdapter which creates the grid view for the day fields
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Setting the layout attribute with the GridLayout to creating the static method refreshMainLayout()
        layout = findViewById(R.id.grid);
        layout.setAdapter(adapter);

        // Setting a onClickListener on the calendar view for switching to ChangeDateActivity by clicking
        ImageView calendar = findViewById(R.id.calendar_background);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * This method is called in class ToDoActivity, menuButton.setOnClickListener to refresh the layout
     * of the particular ToDoBars
     */
    public static void refreshMainLayout() {
        adapter.notifyDataSetChanged();
        layout.invalidateViews();
        layout.setAdapter(adapter);
    }
}