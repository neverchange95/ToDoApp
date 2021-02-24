package com.application;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is a adapter for the GridView in the ShowPastToDoActivity. It creates all TextViews for the
 * todos from a specific date, which has choosed from the user in the ChangeDateActivity.
 */
public class PastToDoAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater;
    private static ArrayList<String> toDoPast;
    private PastToDoAdapter adapter;
    private View rowView = null;
    private PastToDoAdapterHolder holder = new PastToDoAdapterHolder(); // Handles all TextViews for the the todos

    /**
     * Constructor for this class to set some attributes
     * @param c Is the Context from which activity the class is called (ShowPastToDoActivity)
     */
    public PastToDoAdapter(Context c) {
        super();
        this.context = c;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @return The length of the days array attribute
     */
    @Override
    public int getCount() {
        return toDoPast.size();
    }

    /**
     * @param position Contains the position of an object which is needed from an array
     * @return The object in an array at a certain position
     */
    @Override
    public Object getItem(int position) {
        return toDoPast.get(position);
    }

    /**
     * @param position Contains the position of an object which is needed from an array
     * @return Always 0, because a itemId isn´t needed
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * This method returns a view that displays the data at the specified position in the data set
     * @param position The position of the item within the adapter's data set of the item whose view we want
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            adapter = this;
            rowView = inflater.inflate(R.layout.past_todo, null);
            holder.todo = rowView.findViewById(R.id.textToDo);

            if(toDoPast.get(position).contains(",DONE")) {
                // crossing out the string, if the task is done
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

    /**
     * This method is called in ChangeDateActivity to set the array list of all todos here
     * @param a Contains a list with all todos of a specific choosed date
     */
    public static void setToDoAdapterArray(ArrayList<String> a) {
        toDoPast = a;
    }
}
