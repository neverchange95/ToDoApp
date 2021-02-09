package com.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater2;
    private View rowView2 = null;
    private ToDoAdapterHolder holder = new ToDoAdapterHolder();
    private static ArrayList<String> toDoCurrentDate;

    // Only for testing
//    String[] testInput = {
//            "Schlafen",
//            "Essen",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//            "Programmieren",
//    };

    public ToDoAdapter(Context c) {
        super();
        this.context = c;
        inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return toDoCurrentDate.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoCurrentDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            rowView2 = inflater2.inflate(R.layout.todo, null);
            holder.check = (Button) rowView2.findViewById(R.id.checkButton);
            holder.delete = (Button) rowView2.findViewById(R.id.delete_button);
            holder.todo = (TextView) rowView2.findViewById(R.id.input_todo);
        //    holder.todo.setText(toDoCurrentDate.get(position));
        } else {
            rowView2 = convertView;
        }
        return rowView2;
    }

    public static void setToDoAdapterArray(ArrayList<String> a) {
        toDoCurrentDate = a;
    }
}
