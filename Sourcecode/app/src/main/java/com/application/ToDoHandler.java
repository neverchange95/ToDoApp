package com.application;

import java.util.ArrayList;
import java.util.HashMap;

public class ToDoHandler {
    private DateHandler dateHandler = DateHandler.getInstance();
    private static HashMap<String, ArrayList<String>> monthToDos = new HashMap<>();

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

    public ArrayList<String> getToDoArray(String key) {
        return monthToDos.get(key);
    }

    public static void setToDoArray(String todo, String key) {
        ArrayList<String> toDoList = monthToDos.get(key);
        toDoList.add(todo);
        monthToDos.put(key,toDoList);
        System.out.println(monthToDos.get(key));
    }


















    // So bekommt man den Zugriff auf das Array von anderen Klassen aus
//    public static int[] test = new int[]{1, 2, 3};
//
//    public static int[] getArray() {
//        return test;
//    }

}
