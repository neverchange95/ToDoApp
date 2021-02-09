package com.application;

public class ToDoHandler {
    private DateHandler dateHandler = DateHandler.getInstance();

    // Jeder Tag bekommt ein eigenes Array mit den Todos
    // Die MainActivity soll immer nur den aktuellen Monat anzeigen -> Die ToDoArrays für den aktuellen monat müssen lokal auf dem Smartphone liegen
    // Wählt der Benutzer über den Kalender einen anderen Monat bzw. Jahr aus, soll nur das jeweilige Array für die Todos des ausgewählten Tag/Monat/Jahr angzeigt werden

    // Idee: HashMap mit key=Datum, value=todoarray











    // So bekommt man den Zugriff auf das Array von anderen Klassen aus
//    public static int[] test = new int[]{1, 2, 3};
//
//    public static int[] getArray() {
//        return test;
//    }

}
