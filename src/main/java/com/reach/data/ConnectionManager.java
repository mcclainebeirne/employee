package com.reach.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static ConnectionManager instance = null;

    private Connection connection;

    private ConnectionManager() {
        // Establish DB connection using JDBC/JPA ...
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "");
        } catch (Exception e) {
        }
        }

    public static ConnectionManager getInstance() {
        if (instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void releaseConnection() {
        // return connection to pool of connections
    }
}
