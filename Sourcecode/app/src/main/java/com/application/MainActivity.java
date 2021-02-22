package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.TestLooperManager;
import android.provider.Settings;
import android.view.View;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static String MyPREFERENCES = "MyPREFERENCES";
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

        // Create a new connection to database
        try {
            new MySQLConnection();
            Toast.makeText(this,"Verbindung zur Datenbank war erfolgreich!",Toast.LENGTH_SHORT).show();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        this.day = findViewById(R.id.day);
        this.month = findViewById(R.id.month);
        this.year = findViewById(R.id.year);

        day.setText(dh.getDay());
        month.setText(dh.getMonth());
        year.setText(dh.getYear());


        String[] days = dh.getAllDaysFormatted(); // get array with all days of the current month formatted like Mo. 01 from class DateHandler
        try {
            adapter = new DayAdapter(this,days,dh.getDay());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        layout = findViewById(R.id.grid);
        layout.setAdapter(adapter);


        ImageView calendar = findViewById(R.id.calendar_background);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChangeDateActivity.class);
                startActivity(i);
            }
        });
    }

    // This method is called in ToDOActivity in menuButton, to refreshing the ToDoBars
    public static void refreshMainLayout() {
        adapter.notifyDataSetChanged();
        layout.invalidateViews();
        layout.setAdapter(adapter);
    }
}