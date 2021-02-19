package com.application;

import java.util.ArrayList;

public class PastToDoHandler {

    private static ArrayList<String> calendarChoosedTodos = new ArrayList<>(); // the tasks for the day which was choosed in the calendar

    public PastToDoHandler(String date) {
        // adding the tasks for the choosed day from calendar here (get it from sql database)
        for(int i = 0; i < 3; i++) {
            // only for testing
            calendarChoosedTodos.add("Test " + i);
        }
    }

    public ArrayList<String> getCalendarChoosedTodos() {
        return calendarChoosedTodos;
    }
}
