package com.reach.dp;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class JDBCTest {

    public static void main(String[] args) {


        try{
            // 1. Load JDBC Driver for MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to the database (use user and password if necessary
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root", "");
//            System.out.println("I'm connected!");

            // 3. Execute a simple query to read a full table
            String query = "SELECT * from EMPLOYEES;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData meta = rs.getMetaData();
            int colums = meta.getColumnCount();

            // 4. Display results on screen
            while (rs.next()) {
                System.out.println("(");
                for(int i = 1; i <= colums; i++) {
                    String label = meta.getColumnLabel(i);
                    String value = rs.getObject(i).toString();
                    System.out.println("    " + label + ":" + value );
                }
                System.out.println(")");
            }

            conn.close();

        }catch(Exception e){
            System.out.println(e);
        }



    }
}