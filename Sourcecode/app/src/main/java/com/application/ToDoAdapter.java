package com.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ToDoAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater2;
    private View rowView2 = null;
    private ToDoAdapterHolder holder = new ToDoAdapterHolder();

    // Only for testing
    String[] testInput = {
            "Schlafen",
            "Essen",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
            "Programmieren",
    };

    public ToDoAdapter(Context c) {
        super();
        this.context = c;
        inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return testInput.length;
    }

    @Override
    public Object getItem(int position) {
        return testInput[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rowView2 = inflater2.inflate(R.layout.todo,null);
        holder.check = (Button) rowView2.findViewById(R.id.checkButton);
        holder.delete = (Button) rowView2.findViewById(R.id.delete_button);
        holder.todo = (TextView) rowView2.findViewById(R.id.input_todo);
        return rowView2;
    }
}
