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

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This is a adapter for the GridView in the MainActivity. It creates all day fields, which are handling
 * the different todoLists for each day. By clicking on a field the app is switching to the ToDoActivity.
 * Additionally each field shows either a picture of a empty set which is representing a empty todoList or
 * some bars which are supposed to show the filling quantity of the todoList
 */
public class DayAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater;
    private String[] days;
    private DayAdapterHolder dayAdapterHolder = new DayAdapterHolder(); // Handles all ImageViews and TextView for the various day fields
    private View rowView = null;
    private String actualday;
    private DateHandler dh = DateHandler.getInstance(); // Getting a instance from DayHandler to get the various days
    private ToDoHandler toDoHandler = new ToDoHandler(); // Saves all todos

    /**
     * Constructor for this class, to set some attributes.
     * @param c Is the Context from which activity the class is called (MainActivity)
     * @param d Contains all days of the current month
     * @param actualDay Contains the actual day in the current month
     * @throws SQLException Is needed because the ToDoHandler attribute can throw a SQLException
     */
    public DayAdapter(Context c, String[] d, String actualDay) throws SQLException {
        super();
        this.context = c;
        this.days = d;
        this.actualday = actualDay;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @return The length of the days array attribute
     */
    @Override
    public int getCount() {
        return days.length;
    }

    /**
     * @param position Contains the position of an object which is needed from an array
     * @return The object in an array at a certain position
     */
    @Override
    public Object getItem(int position) {
        return days[position];
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
     * @param convertView The old view to reuse, if possible. It is here not used, because it produces a error in the layout
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (days[position].contains("So.")) {
            // The current one day is sunday
            if (!days[position].equals(actualday)) {
                // The current one day is not the actual day in calendar, so the sunday.xml layout must be used
                rowView = inflater.inflate(R.layout.sunday, null);
                dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.sunday_text);
                dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.sunday);
                dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
                dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.sunday_bar1);
                dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.sunday_bar2);
                dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.sunday_bar3);
                // Show or not show the individual bars for the visualisation of the todoElements
                if (toDoHandler.getToDoArray(days[position]).size() == 0) {
                    // No todoElement, show the empty set picture
                    dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
                } else if (toDoHandler.getToDoArray(days[position]).size() == 1) {
                    // One todoElement, show one bar
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                } else if (toDoHandler.getToDoArray(days[position]).size() == 2) {
                    // Two todoElements, show two bars
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                } else if (toDoHandler.getToDoArray(days[position]).size() >= 3) {
                    // Three or more todoElements, show three bars
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                    dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
                }
                dayAdapterHolder.txV.setText(days[position]); // Setting the particular day in the TextView
                // Set a onClickListener for switching to ToDoActivity, if user clicks on the day field
                dayAdapterHolder.imV.setOnClickListener(v -> {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the array list of todos in ToDoAdapter returned from ToDoHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                });
            } else {
                // The current one day is the actual day in calendar, so the actual_day.xml layout and not sunday.xml must be used
                rowView = inflater.inflate(R.layout.actual_day, null);
                dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.actual_day_text);
                dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.actual_day);
                dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
                dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.actual_day_bar1);
                dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.actual_day_bar2);
                dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.actual_day_bar3);
                // Show or not show the individual bars for the visualisation of the todoElements
                if (toDoHandler.getToDoArray(days[position]).size() == 0) {
                    // No todoElement, show the empty set picture
                    dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
                } else if (toDoHandler.getToDoArray(days[position]).size() == 1) {
                    // One todoElement, show one bar
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                } else if (toDoHandler.getToDoArray(days[position]).size() == 2) {
                    // Two todoElements, show two bars
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                } else if (toDoHandler.getToDoArray(days[position]).size() >= 3) {
                    // Three or more todoElements, show three bars
                    dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                    dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                    dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
                }
                dayAdapterHolder.txV.setText(days[position]); // Setting the particular day in the TextView
                // Set a onClickListener for switching to ToDoActivity, if user clicks on the day field
                dayAdapterHolder.imV.setOnClickListener(v -> {
                    dh.setDay(days[position]); // Setting the choosed day in DateHandler
                    ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the array list of todos in ToDoAdapter returned from ToDoHandler
                    Intent i = new Intent(context, ToDoActivity.class);
                    context.startActivity(i);
                });
            }
        } else if (days[position].equals(actualday)) {
            // The current one day is a normal week day (included sunday) and the actual day in calendar, so the actual_day.xml layout must be used
            rowView = inflater.inflate(R.layout.actual_day, null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.actual_day_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.actual_day);
            dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
            dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.actual_day_bar1);
            dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.actual_day_bar2);
            dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.actual_day_bar3);
            // Show or not show the individual bars for the visualisation of the todoElements
            if (toDoHandler.getToDoArray(days[position]).size() == 0) {
                // No todoElement, show the empty set picture
                dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
            } else if (toDoHandler.getToDoArray(days[position]).size() == 1) {
                // One todoElement, show one bar
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
            } else if (toDoHandler.getToDoArray(days[position]).size() == 2) {
                // Two todoElements, show two bars
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
            } else if (toDoHandler.getToDoArray(days[position]).size() >= 3) {
                // Three or more todoElements, show three bars
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
            }
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(v -> {
                dh.setDay(days[position]); // Setting the particular day in the TextView
                ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the array list of todos in ToDoAdapter returned from ToDoHandler
                Intent i = new Intent(context, ToDoActivity.class);
                context.startActivity(i);
            });
        } else {
            // The current one day is a normal week day (included sunday), so the normal_day.xml must be used
            rowView = inflater.inflate(R.layout.normal_day, null);
            dayAdapterHolder.txV = (TextView) rowView.findViewById(R.id.normal_day_text);
            dayAdapterHolder.imV = (ImageView) rowView.findViewById(R.id.normal_day);
            dayAdapterHolder.imVEmpty = (ImageView) rowView.findViewById(R.id.empty_todolist);
            dayAdapterHolder.imVBar1 = (ImageView) rowView.findViewById(R.id.normal_day_bar1);
            dayAdapterHolder.imVBar2 = (ImageView) rowView.findViewById(R.id.normal_day_bar2);
            dayAdapterHolder.ImVBar3 = (ImageView) rowView.findViewById(R.id.normal_day_bar3);
            // Show or not show the individual bars for the visualisation of the todoElements
            if (toDoHandler.getToDoArray(days[position]).size() == 0) {
                // No todoElement, show the empty set picture
                dayAdapterHolder.imVEmpty.setVisibility(View.VISIBLE);
            } else if (toDoHandler.getToDoArray(days[position]).size() == 1) {
                // One todoElement, show one bar
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
            } else if (toDoHandler.getToDoArray(days[position]).size() == 2) {
                // Two todoElements, show two bars
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
            } else if (toDoHandler.getToDoArray(days[position]).size() >= 3) {
                // Three or more todoElements, show three bars
                dayAdapterHolder.imVBar1.setVisibility(View.VISIBLE);
                dayAdapterHolder.imVBar2.setVisibility(View.VISIBLE);
                dayAdapterHolder.ImVBar3.setVisibility(View.VISIBLE);
            }
            dayAdapterHolder.txV.setText(days[position]);
            dayAdapterHolder.imV.setOnClickListener(v -> {
                dh.setDay(days[position]); // Setting the particular day in the TextView
                ToDoAdapter.setToDoAdapterArray(toDoHandler.getToDoArray(days[position]), days[position]); // Set the array list of todos in ToDoAdapter returned from ToDoHandler
                Intent i = new Intent(context, ToDoActivity.class);
                context.startActivity(i);
            });
        }
        return rowView;
    }
}
