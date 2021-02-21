package com.application;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class PastToDoAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater;
    private static ArrayList<String> toDoPast;
    private PastToDoAdapter adapter;
    private View rowView = null;
    private PastToDoAdapterHolder holder = new PastToDoAdapterHolder();

    public PastToDoAdapter(Context c) {
        super();
        this.context = c;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return toDoPast.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoPast.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            adapter = this;
            rowView = inflater.inflate(R.layout.past_todo, null);
            holder.todo = rowView.findViewById(R.id.textToDo);

            // crossing out the string, if the task is done
            if(toDoPast.get(position).contains(",DONE")) {
                String task = toDoPast.get(position).substring(0,toDoPast.get(position).indexOf(",DONE"));
                holder.todo.setText(task);
                holder.todo.setPaintFlags(holder.todo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.todo.setText(toDoPast.get(position));
            }
        } else {
            rowView = convertView;
        }
        return rowView;
    }


    // this method is called in ChangeDateActivity
    public static void setToDoAdapterArray(ArrayList<String> a) {
        for(int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        toDoPast = a;
    }
}
