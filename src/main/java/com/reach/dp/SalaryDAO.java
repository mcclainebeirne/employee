package com.reach.dp;

import com.reach.data.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class SalaryDAO {

    private ConnectionManager connectionManager;

    private static SalaryDAO instance = null; // Implements Singleton GOF Pattern

    private SalaryDAO() {
        connectionManager = ConnectionManager.getInstance();
    }

    public static SalaryDAO getInstance() {
        if (instance == null)
            instance = new SalaryDAO();
        return instance;
    }

    public void insert(Salary s) {
        try {
            String selectTableSQL = "INSERT INTO salaries (emp_no, salary, from_date, to_date) " +
                    "VALUES (" + s.getId() + ", " + s.getSalary() + ", NOW(), NOW());";

            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectTableSQL);
            connectionManager.releaseConnection();
            System.out.println("salary " + s.getSalary() + " Added");
        } catch (SQLException e) {

        }
    }


    public void delete(int id) {
        try {
            String selectTableSQL = "DELETE FROM salaries WHERE emp_no = " + id;
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectTableSQL);
            connectionManager.releaseConnection();
            System.out.println("salary records deleted");
        }
        catch (SQLException e) {

        }

    }

    public void update(int inputSalary, int id) {
        try {
            String selectTableSQL = "UPDATE salaries SET salary = " + inputSalary + " WHERE emp_no = " + id + ";";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectTableSQL);
            connectionManager.releaseConnection();
            System.out.println("salary updated");
        }
        catch (SQLException e) {

        }

    }

    public ArrayList<Salary> getAll() {

        try {
            String selectTableSQL = "SELECT * from salaries";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            connectionManager.releaseConnection();
            return resultSetToEntities(rs);
        }
        catch (SQLException e) {


        }
        return new ArrayList<>();
    }


    public ArrayList<Salary> findByKeyValue(String key, int value) {
        try{
            String selectTableSQL = "SELECT * from salaries WHERE "+key+" = '"+value+"'";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            connectionManager.releaseConnection();
            return resultSetToEntities(rs);

        }
        catch (SQLException e) {

        }
        return new ArrayList<>();
}

    public ArrayList<Salary> findByKeyValueLike(String key, int value) {
        try {
            String selectTableSQL = "SELECT * from salaries WHERE " + key + " LIKE '%" + value + "%'";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            connectionManager.releaseConnection();
        }
        catch (SQLException e) {

        }
        return new ArrayList<>();
    }

    public Salary findById(int inputId) {
        try {
            String selectTableSQL = "SELECT * from EMPLOYEES WHERE emp_no = " + inputId;
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);

            if(rs.next()) {
                Date fromDate = rs.getDate(3);
                Date toDate = rs.getDate(4);
                connectionManager.releaseConnection();
                return new Salary(rs.getInt(1), rs.getInt(2), fromDate, toDate);

            }
        }
        catch (SQLException e) {

        }
        return null;

    }

    private ArrayList<Salary> resultSetToEntities(ResultSet rs) {
        try {
                ArrayList<Salary> salaries = new ArrayList<Salary>();
                while (rs.next()) {
                    Date fromDate = rs.getDate(3);
                    Date toDate = rs.getDate(4);
                    salaries.add(new Salary(rs.getInt(1), rs.getInt(2), fromDate, toDate));
            }
            return salaries;
        }
        catch(SQLException e){

        }
        return new ArrayList<>();

    }

    public static void main(String[] args) throws Exception {

        SalaryDAO dao = SalaryDAO.getInstance();
//         for (Salary s : dao.getAll())
//               System.out.println(s);

//            for (Salary s : dao.findByKeyValue("salary", 71963))
//                System.out.println(s);

       //    System.out.println(dao.findById(10002));

             // dao.insert(new Salary(6,200, new Date(),  new Date()));

        // dao.delete(6);


    }


}

