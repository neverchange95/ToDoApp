package com.application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ToDoHandler {
    private DateHandler dateHandler = DateHandler.getInstance();
    private static HashMap<String, ArrayList<String>> monthToDos = new HashMap<>(); // all months and them tasks for the actual month
    private static ArrayList<String> calendarChoosedTodos = new ArrayList<>(); // the tasks for the day which was choosed in the calendar

    // Jeder Tag bekommt ein eigenes Array mit den Todos
    // Die MainActivity soll immer nur den aktuellen Monat anzeigen -> Die ToDoArrays für den aktuellen monat müssen lokal auf dem Smartphone liegen
    // Wählt der Benutzer über den Kalender einen anderen Monat bzw. Jahr aus, soll nur das jeweilige Array für die Todos des ausgewählten Tag/Monat/Jahr angzeigt werden

    // Idee: HashMap mit key=Datum, value=todoarray

    String[] allDaysInMonth = dateHandler.getAllDaysFormatted();

    public ToDoHandler() {
        for(int i = 0; i < allDaysInMonth.length; i++) {
            monthToDos.put(allDaysInMonth[i],new ArrayList<String>());
        }
    }

    public ToDoHandler(String date) {
        // adding the tasks for the choosed day from calendar here (get it from sql database)
        for(int i = 0; i < 3; i++) {
            // only for testing
            calendarChoosedTodos.add("Test " + i);
        }
    }



    public ArrayList<String> getCalendarChoosedTodos() {
        return calendarChoosedTodos;
    }


    public ArrayList<String> getToDoArray(String key) {
        return monthToDos.get(key);
    }

    public static void setToDoArray(String todo, String key) {
        ArrayList<String> toDoList = monthToDos.get(key);
        toDoList.add(todo);
        monthToDos.put(key,toDoList);
    }
}
