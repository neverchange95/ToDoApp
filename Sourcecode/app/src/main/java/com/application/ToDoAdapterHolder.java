package com.application;

import android.widget.Button;
import android.widget.TextView;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This class is a holder for the grid view of the todoElements in ToDoActivity. It is called in ToDoAdapter and
 * handles all TextViews and Buttons for the different todos
 */
public class ToDoAdapterHolder {
    public TextView todo;
    public Button delete;
    public Button check;

    public ToDoAdapterHolder() {
        // empty
    }
}
