package com.application;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ToDoHandler {
    private DateHandler dateHandler = DateHandler.getInstance();
    private static HashMap<String, ArrayList<String>> monthToDos = new HashMap<>(); // all days and them tasks for the actual month
    private static Connection connection = MySQLConnection.getConnection();


    String[] allDaysInMonth = dateHandler.getAllDaysFormatted();

    public ToDoHandler() throws SQLException {
        for(int i = 0; i < allDaysInMonth.length; i++) {
            //getToDoFromDB(allDaysInMonth[i]);
            ArrayList<String> resultFromDB = getToDoFromDB(allDaysInMonth[i]);
        //    monthToDos.put(allDaysInMonth[i],new ArrayList<String>());
            // allDayInMonth[i] returns Mo. 01
            monthToDos.put(allDaysInMonth[i],resultFromDB);
        }
    }


    public ArrayList<String> getToDoArray(String key) {
        return monthToDos.get(key);
    }

    // is called in ToDoActivity
    public static void setToDoArray(String todo, String keyDate) throws SQLException {
        // keyDate = Mo. 10
        String insertStatement = "INSERT INTO TODO(AUFGABE,DATUM) " + "VALUES(?,?)";
        PreparedStatement pstmtInsert = connection.prepareStatement(insertStatement);
        pstmtInsert.setString(1,todo);
        pstmtInsert.setString(2,keyDate + "." + DateHandler.getActualDate().getMonth() + "." + DateHandler.getActualDate().getYear());
        pstmtInsert.executeUpdate();
        pstmtInsert.close();
        ArrayList<String> toDoList = monthToDos.get(keyDate);
        toDoList.add(todo);
        monthToDos.put(keyDate,toDoList);
    }

    // is called in Constructor
    public static ArrayList<String> getToDoFromDB(String date) throws SQLException {
        String correctDateForDatabase = date + "." + DateHandler.getActualDate().getMonth() + "." + DateHandler.getActualDate().getYear(); // date has only Mo. 01 but we need for database Mo. 01.Februar.2021
        Statement statement = connection.createStatement();
        String selectStatement = "SELECT AUFGABE FROM TODO WHERE DATUM = " + "'" + correctDateForDatabase + "'";
        ResultSet resultSet = statement.executeQuery(selectStatement);
        ArrayList<String> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getString(1));
        }
        resultSet.close();
        statement.close();
        return results;
    }

    // is called in ToDoAdapter
    public static void updateDB(String doneToDo, String choosedDay) throws SQLException{
        String dateFormatForDB = choosedDay + "." + DateHandler.getActualDate().getMonth() + "." + DateHandler.getActualDate().getYear();
        String taskWithoutFlag = doneToDo.substring(0,doneToDo.indexOf(",DONE"));
        String updateStatement = "UPDATE TODO SET AUFGABE=" + "'" + doneToDo + "'" + "WHERE AUFGABE=" + "'" + taskWithoutFlag + "'" + "AND DATUM = " + "'" + dateFormatForDB + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateStatement);
        statement.close();
    }

    // is called in ToDoAdapter
    public static void deleteToDoInDB(String deleteToDo, String choosedDay) throws SQLException {
        String dateFormatForDB = choosedDay + "." + DateHandler.getActualDate().getMonth() + "." + DateHandler.getActualDate().getYear();
        String deleteStatement = "DELETE FROM TODO WHERE AUFGABE = " + "'" +  deleteToDo + "'" + "AND DATUM = " + "'" + dateFormatForDB + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(deleteStatement);
        statement.close();
    }
}
