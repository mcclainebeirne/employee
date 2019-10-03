package com.reach.dp.MockDataToXML;

import com.reach.data.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;


public class MetaDataTest {

    private static ConnectionManager connectionManager;

    private static MetaDataTest instance = null;

    private MetaDataTest() {
        connectionManager = ConnectionManager.getInstance();
    }

    public static MetaDataTest getInstance() {
        if (instance == null)
            instance = new MetaDataTest();
        return instance;
    }

    public static void main(String[] args) {
        MetaDataTest dao = MetaDataTest.getInstance();

       // ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Table> tables = new ArrayList<>();



        // Execute a simple query to read a the table
        try {
            Connection connection = connectionManager.getConnection();
            // 3. Execute a simple query to read a full table
            String query = "SELECT * from EMPLOYEES;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData meta = rs.getMetaData();
            int colums = meta.getColumnCount();

            // 4. Display results on screen
            while (rs.next()) {
                System.out.println("(");
                for (int i = 1; i <= colums; i++) {
                    String label = meta.getColumnLabel(i);
                    String value = rs.getObject(i).toString();
                    String type = meta.getColumnTypeName(i);
                    System.out.println("    " + label + ":" + value + ":" + type);
                }
                System.out.println(")");
            }

            connection.close();

        } catch (
                Exception e) {
            System.out.println(e);
        }

    }
}