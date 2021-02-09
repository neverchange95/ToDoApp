package com.application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DayAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater;
    private String[] days;
    private DayAdapterHolder dayAdapterHolder = new DayAdapterHolder();
    private View rowView = null;
    private String actualday;
    private DateHandler dh = DateHandler.getInstance();


    public DayAdapter(Context c, String[] d, String actualDay) {
        super();
        this.context = c;
        this.days = d;
        this.actualday = actualDay;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public Object getItem(int position) {
        return days[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(days[position].contains("So.")) {
            rowView = inflater.inflate(R.layout.sunday,null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.sunday_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.sunday);
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        } else if(days[position].equals(actualday)) {
            rowView = inflater.inflate(R.layout.actual_day, null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.actual_day_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.actual_day);
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        } else {
            rowView = inflater.inflate(R.layout.normal_day,null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.normal_day_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.normal_day);
            dayAdapterHolder.imVBar = (ImageView) rowView.findViewById(R.id.normal_day_bar1);
            // Bar1 von normal_day anzeigen, ist im xml hided!
            dayAdapterHolder.imVBar.setVisibility(View.VISIBLE);
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        }
        return rowView;
    }
}
