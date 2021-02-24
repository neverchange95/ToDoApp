package com.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PastToDoHandler {

    private ArrayList<String> calendarChoosedTodos = new ArrayList<>(); // the tasks for the day which was choosed in the calendar

    public PastToDoHandler(String date) throws SQLException {
        // adding the tasks for the choosed day from calendar here (get it from sql database)
        getPastToDoFromDB(date);
    }

    public ArrayList<String> getCalendarChoosedTodos() {
        return calendarChoosedTodos;
    }

    public void getPastToDoFromDB(String date) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT AUFGABE FROM TODO WHERE DATUM = " + "'" + date + "'");

        while(resultSet.next()) {
            System.out.println(resultSet.getString(1));
            calendarChoosedTodos.add(" - " + resultSet.getString(1));
        }
        statement.close();
        resultSet.close();
    }
}
