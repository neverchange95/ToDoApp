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
    private Holder holder = new Holder();
    private View rowView = null;

    public DayAdapter(Context c, String[] d) {
        super();
        this.context = c;
        this.days = d;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(days[position].contains("So.")) {
            rowView = inflater.inflate(R.layout.sunday,null);
            holder.txV = (TextView) rowView.findViewById(R.id.sunday_text);
            holder.imV = (ImageView) rowView.findViewById(R.id.sunday);
            holder.txV.setText(days[position]);
            holder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        } else if(position == 0) {
            rowView = inflater.inflate(R.layout.actual_day, null);
            holder.txV = (TextView) rowView.findViewById(R.id.actual_day_text);
            holder.imV = (ImageView) rowView.findViewById(R.id.actual_day);
            holder.txV.setText(days[position]);
            holder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        } else {
            rowView = inflater.inflate(R.layout.normal_day,null);
            holder.txV = (TextView) rowView.findViewById(R.id.normal_day_text);
            holder.imV = (ImageView) rowView.findViewById(R.id.normal_day);
            holder.imVBar = (ImageView) rowView.findViewById(R.id.normal_day_bar1);
            // Bar1 von normal_day anzeigen, ist im xml hided!
            holder.imVBar.setVisibility(View.VISIBLE);
            holder.txV.setText(days[position]);
            holder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        }
        return rowView;
    }
}
