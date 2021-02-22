package com.application;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static Connection connection = null;

    public MySQLConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // This is needed to avoid NetworkOnMainThreadException and get connection to database
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Connect to database
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://THAT´S SECRET :D");
        if(!connection.isClosed()) {
            System.out.println("Successfully connected!");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
