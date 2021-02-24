package com.application;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author neverchange95
 * @version 02/2021
 * 
 * This class creates a connection to the SQL-Database. It is called in MainActivity by starting the app
 */
public class MySQLConnection {
    private static Connection connection = null;

    /**
     * Constructor which is called in MainActivity and creates a new connection to database
     * @throws SQLException Can be throw by getting the connection
     * @throws ClassNotFoundException Can be throw by looking for the driver
     * @throws InstantiationException Can be throw by creating a new instance of the driver
     * @throws IllegalAccessException Can be throw by creating a new instance of the driver
     */
    public MySQLConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // This is needed to avoid NetworkOnMainThreadException and get connection to database
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Connect to database
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://IP-Adress:3306/Database-Name","Database-User","Database-User-Password"); // parameters: url, user, password
    }

    /**
     * @return The connection to the database
     */
    public static Connection getConnection() {
        return connection;
    }
}
