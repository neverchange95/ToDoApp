package com.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PastToDoHandler {

    private ArrayList<String> calendarChoosedTodos = new ArrayList<>(); // the tasks for the day which was choosed in the calendar

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

    public void testDB() throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT AUFGABE FROM TODO");

        while(resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
    }
}
