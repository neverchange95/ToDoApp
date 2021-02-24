package com.application;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is a adapter for the GridView in the ToDoActivity. It creates all TextViews and Buttons for the
 * todos from a specific choosed day in the MainActivity.
 * It creates the TextView with the todoElement, a button to delete a todoElement and a button to make a todoElement done
 */
public class ToDoAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater2;
    private View rowView2 = null;
    private static ArrayList<String> toDoCurrentDate;
    private static String choosedDay;
    private ArrayList<ToDoAdapterHolder> todoElements = new ArrayList<>(); // Handles all ImageViews and TextView for the various todoFields in a list
    private ToDoAdapter adapter;

    /**
     * Constructor for this class to set some attributes
     * @param c Is the Context from which activity the class is called (ToDoActivity)
     */
    public ToDoAdapter(Context c) {
        super();
        this.context = c;
        inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @return The length of the days array attribute
     */
    @Override
    public int getCount() {
        return toDoCurrentDate.size();
    }

    /**
     * @param position Contains the position of an object which is needed from an array
     * @return The object in an array at a certain position
     */
    @Override
    public Object getItem(int position) {
        return toDoCurrentDate.get(position);
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        if(convertView == null) {
            adapter = this;
            todoElements.add(new ToDoAdapterHolder());
            rowView2 = inflater2.inflate(R.layout.todo, null);
            todoElements.get(position).check = rowView2.findViewById(R.id.checkButton);
            todoElements.get(position).delete = rowView2.findViewById(R.id.delete_button);
            todoElements.get(position).todo = rowView2.findViewById(R.id.textToDo);

            if(toDoCurrentDate.get(position).contains(",DONE")) {
                // crossing out the string, if the task is done
                String task = toDoCurrentDate.get(position).substring(0,toDoCurrentDate.get(position).indexOf(",DONE"));
                todoElements.get(position).todo.setText(task);
                todoElements.get(position).todo.setPaintFlags(todoElements.get(position).todo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                todoElements.get(position).todo.setText(toDoCurrentDate.get(position));
            }

            // set flag to cross out the element, if the user click on the check button
            todoElements.get(position).check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDoCurrentDate.set(position, toDoCurrentDate.get(position) + ",DONE"); // Setting a Flag into to the array, if the task is done
                    try {
                        ToDoHandler.updateDB(toDoCurrentDate.get(position), choosedDay); // Update the element also in the database by setting the flag (",DONE")
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    todoElements.get(position).todo.setPaintFlags(todoElements.get(position).todo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });

            // delete a todoElement, if the user click on the delete button
            todoElements.get(position).delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todoElements.remove(position); // Remove the element from the ToDoAdapterHolder list
                    try {
                        ToDoHandler.deleteToDoInDB(toDoCurrentDate.get(position), choosedDay); // Remove the element from the database
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    toDoCurrentDate.remove(position); // Remove the element from the array list, which contains all todos for a specific date
                    ToDoActivity.refreshLayout();
                }
            });
        } else {
            rowView2 = convertView;
        }
        return rowView2;
    }

    // this method is called in DayAdapter

    /**
     * This Method is called in class DayAdapter to set the array list with the todoElements for the choosed date
     * @param a Contains a array list with all todoElements of the choosed date
     * @param d Contains the choosed day like Mo. 01
     */
    public static void setToDoAdapterArray(ArrayList<String> a, String d) {
        toDoCurrentDate = a;
        choosedDay = d;
    }
}
