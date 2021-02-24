package com.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This class is for getting the different todos from the database and save these in a array list.
 */
public class ToDoHandler {
    private DateHandler dateHandler = DateHandler.getInstance();
    private static HashMap<String, ArrayList<String>> monthToDos = new HashMap<>(); // all days and them tasks for the actual month
    private static Connection connection = MySQLConnection.getConnection();

    /**
     * The constructor for this class calls the method getToDoFromDB which returns all todos of a specific date from database and
     * put these result into the hash map monthToDos with the date as key
     * @throws SQLException Can throw a SQLException because the method getToDoFromDB is connecting to the database
     */
    public ToDoHandler() throws SQLException {
        String[] allDaysInMonth = dateHandler.getAllDaysFormatted(); // Get all days of a specific month from class DateHandler formatted like Mo. 01
        for(int i = 0; i < allDaysInMonth.length; i++) {
            ArrayList<String> resultFromDB = getToDoFromDB(allDaysInMonth[i]);
            monthToDos.put(allDaysInMonth[i],resultFromDB);
        }
    }

    /**
     * This method returns a array list of all todos at a specific day from the hash map monthToDos. It is called in class DayAdapter
     * @param key Contains a specific day formatted like Mo. 01
     * @return A array list of all todos ast a specific day
     */
    public ArrayList<String> getToDoArray(String key) {
        return monthToDos.get(key);
    }

    // is called in ToDoActivity

    /**
     * This method is setting a new todoElement into the monthToDos hash map and also into the database. It is called in ToDoActivity
     * @param todo Contains a new todoElement
     * @param keyDate Contains a specific date formatted like Mo. 01
     * @throws SQLException Can throw a SQLException because the method is connecting to the database
     */
    public static void setToDoArray(String todo, String keyDate) throws SQLException {
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

    /**
     * This method is getting all todoElements of a specific date from the database. It is called in the constructor of this class
     * @param date Contains a specific date like Mo. 01
     * @return A array list of all todos at a specific date
     * @throws SQLException Can throw a SQLException because the method is connecting to the database
     */
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

    /**
     * This method is updating the database, if the user set a todoElement as done. It is called in the class ToDoAdapter
     * @param doneToDo Contains the todoElement which is done
     * @param choosedDay Contains the specific day on which the todoElement was created
     * @throws SQLException Can throw a SQLException because the method is connecting to the database
     */
    public static void updateDB(String doneToDo, String choosedDay) throws SQLException{
        String dateFormatForDB = choosedDay + "." + DateHandler.getActualDate().getMonth() + "." + DateHandler.getActualDate().getYear();
        String taskWithoutFlag = doneToDo.substring(0,doneToDo.indexOf(",DONE"));
        String updateStatement = "UPDATE TODO SET AUFGABE=" + "'" + doneToDo + "'" + "WHERE AUFGABE=" + "'" + taskWithoutFlag + "'" + "AND DATUM = " + "'" + dateFormatForDB + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateStatement);
        statement.close();
    }

    // is called in ToDoAdapter

    /**
     * This method is deleting a todoElement from the database. It is called in the class TodoAdapter
     * @param deleteToDo Contains the todoElement which should be delete from database
     * @param choosedDay Contains the specific day on which the todoElement was created
     * @throws SQLException Can throw a SQLException because the method is connecting to the database
     */
    public static void deleteToDoInDB(String deleteToDo, String choosedDay) throws SQLException {
        String dateFormatForDB = choosedDay + "." + DateHandler.getActualDate().getMonth() + "." + DateHandler.getActualDate().getYear();
        String deleteStatement = "DELETE FROM TODO WHERE AUFGABE = " + "'" +  deleteToDo + "'" + "AND DATUM = " + "'" + dateFormatForDB + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(deleteStatement);
        statement.close();
    }
}
