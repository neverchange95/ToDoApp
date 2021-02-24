package com.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This class is for getting the different past todos from the database and save these in a array list.
 */
public class PastToDoHandler {
    private ArrayList<String> calendarChoosedTodos = new ArrayList<>(); // save the todos for the day which was choosed in the calendar

    /**
     * The constructor for this class is calling only one method (getPastToDoFromDB). This will trigger
     * the getting of all todos for a choosed date
     * @param date Contains a date from which the user would like to know his todos
     * @throws SQLException Can throw a SQLException because the method which is called is connecting to the database
     */
    public PastToDoHandler(String date) throws SQLException {
        getPastToDoFromDB(date);
    }

    /**
     * This Method is called in ChangeDateActivity
     * @return The array list of all todos on the date which was choosed from the user.
     */
    public ArrayList<String> getCalendarChoosedTodos() {
        return calendarChoosedTodos;
    }

    /**
     * This method is selecting all todos on a specific date from the database and save these in the array list.
     * It is called in the constructor here
     * @param date Contains a date from which the user would like to know this todos
     * @throws SQLException Can throw a SQLException because the method is connecting to the database
     */
    public void getPastToDoFromDB(String date) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT AUFGABE FROM TODO WHERE DATUM = " + "'" + date + "'");

        while(resultSet.next()) {
            calendarChoosedTodos.add(" - " + resultSet.getString(1)); // Save the todos in the array list
        }
        statement.close();
        resultSet.close();
    }
}
