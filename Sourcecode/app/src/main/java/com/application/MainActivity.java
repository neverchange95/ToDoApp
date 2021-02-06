package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        String[] days = {"Mo. 01", "Di. 02", "Mi. 03", "Do. 05", "Fr. 06", "Sa. 07", "So. 08", "Mo. 09", "Di. 10", "Mi. 11", "Do. 12", "Fr. 13", "Sa. 14", "So. 15", "Mo. 16", "Di. 17", "Mi. 18", "Do. 19", "Fr. 20", "Sa. 21", "So. 22", "Mo. 23", "Di. 24", "Mi. 25", "Do. 26", "Fr. 27", "Sa. 28", "So. 29", "Mo. 30", "Di. 31"};
        GridView layout = findViewById(R.id.grid);
        layout.setAdapter(new DayAdapter(this, days));

    }
}