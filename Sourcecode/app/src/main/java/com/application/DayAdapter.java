package com.application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;

public class DayAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater;
    private String[] days;
    private DayAdapterHolder dayAdapterHolder = new DayAdapterHolder();
    private View rowView = null;
    private String actualday;
    private DateHandler dh = DateHandler.getInstance();
    private ToDoHandler toDoHandler = new ToDoHandler();


    public DayAdapter(Context c, String[] d, String actualDay) throws SQLException {
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
            if(!days[position].equals(actualday)) {
                rowView = inflater.inflate(R.layout.sunday, null);
                dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.sunday_text);
                dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.sunday);
                dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
                dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.sunday_bar1);
                dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.sunday_bar2);
                dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.sunday_bar3);
                // Bar1 von normal_day anzeigen, ist im xml hided!
                if(toDoHandler.getToDoArray(days[position]).size() == 0) {
                    dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
                }else if(toDoHandler.getToDoArray(days[position]).size() == 1) {
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                } else if(toDoHandler.getToDoArray(days[position]).size() == 2) {
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                } else if(toDoHandler.getToDoArray(days[position]).size() >= 3) {
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                    dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
                }
                dayAdapterHolder.txV.setText(days[position]);
                dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dh.setDay(days[position]); // Setting the choosed day in DateHandler
                        ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the arraylist returned from ToDoHandler
                        Intent i = new Intent(context, ToDoActivity.class);
                        context.startActivity(i);
                    }
                });
            } else {
                rowView = inflater.inflate(R.layout.actual_day, null);
                dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.actual_day_text);
                dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.actual_day);
                dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
                dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.actual_day_bar1);
                dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.actual_day_bar2);
                dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.actual_day_bar3);
                // Bar1 von normal_day anzeigen, ist im xml hided!
                if(toDoHandler.getToDoArray(days[position]).size() == 0) {
                    dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
                }else if(toDoHandler.getToDoArray(days[position]).size() == 1) {
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                } else if(toDoHandler.getToDoArray(days[position]).size() == 2) {
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                } else if(toDoHandler.getToDoArray(days[position]).size() >= 3) {
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                    dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
                }
                dayAdapterHolder.txV.setText(days[position]);
                dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dh.setDay(days[position]); // Setting the choosed day in DateHandler
                        ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the arraylist returned from ToDoHandler
                        Intent i = new Intent(context, ToDoActivity.class);
                        context.startActivity(i);
                    }
                });
            }
        } else if(days[position].equals(actualday)) {
            rowView = inflater.inflate(R.layout.actual_day, null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.actual_day_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.actual_day);
            dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
            dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.actual_day_bar1);
            dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.actual_day_bar2);
            dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.actual_day_bar3);
            // Bar1 von normal_day anzeigen, ist im xml hided!
            if(toDoHandler.getToDoArray(days[position]).size() == 0) {
                dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
            }else if(toDoHandler.getToDoArray(days[position]).size() == 1) {
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
            } else if(toDoHandler.getToDoArray(days[position]).size() == 2) {
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
            } else if(toDoHandler.getToDoArray(days[position]).size() >= 3) {
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
            }
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the arraylist returned from ToDoHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        } else {
            rowView = inflater.inflate(R.layout.normal_day,null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.normal_day_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.normal_day);
            dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
            dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.normal_day_bar1);
            dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.normal_day_bar2);
            dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.normal_day_bar3);
            // Bar1 von normal_day anzeigen, ist im xml hided!
            if(toDoHandler.getToDoArray(days[position]).size() == 0) {
                dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
            }else if(toDoHandler.getToDoArray(days[position]).size() == 1) {
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
            } else if(toDoHandler.getToDoArray(days[position]).size() == 2) {
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
            } else if(toDoHandler.getToDoArray(days[position]).size() >= 3) {
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
            }
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the arraylist returned from ToDoHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                }
            });
        }
        return rowView;
    }
}
