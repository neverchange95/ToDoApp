package com.application;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater2;
    private View rowView2 = null;
    private static ArrayList<String> toDoCurrentDate;
    private ArrayList<ToDoAdapterHolder> todoElements = new ArrayList<>();
    ToDoAdapter adapter;


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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // Problem:
        if(convertView == null) {
            adapter = this;
            todoElements.add(new ToDoAdapterHolder());
            rowView2 = inflater2.inflate(R.layout.todo, null);
            todoElements.get(position).check = rowView2.findViewById(R.id.checkButton);
            todoElements.get(position).delete = rowView2.findViewById(R.id.delete_button);
            todoElements.get(position).todo = rowView2.findViewById(R.id.textToDo);
            todoElements.get(position).todo.setText(toDoCurrentDate.get(position));

            // set flag to cross out the element
            todoElements.get(position).check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todoElements.get(position).todo.setPaintFlags(todoElements.get(position).todo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });

            // delete a todo
            todoElements.get(position).delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(position);
                    todoElements.remove(position);
                    toDoCurrentDate.remove(position);
                    ToDoActivity.refreshLayout();
                }
            });


        } else {
            rowView2 = convertView;
        }
        return rowView2;
    }

    // this method is called in DayAdapter
    public static void setToDoAdapterArray(ArrayList<String> a) {
        toDoCurrentDate = a;
    }
}
