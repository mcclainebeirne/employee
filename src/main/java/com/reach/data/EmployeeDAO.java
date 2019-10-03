package com.reach.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import com.reach.business.Employee;

public class EmployeeDAO {

    private ConnectionManager connectionManager;

    private static EmployeeDAO instance = null; // Implements Singleton GOF Pattern

    private EmployeeDAO() {
        connectionManager = ConnectionManager.getInstance();
    }

    public static EmployeeDAO getInstance() {
        if (instance == null)
            instance = new EmployeeDAO();
        return instance;
    }

    public void insert(Employee e) {
        try {
            String selectTableSQL = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                    "VALUES (" + e.getId() + ", NOW(), '" + e.getFirstName() + "', '" + e.getLastName() + "', '" + e.getGender() + "', NOW());";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectTableSQL);
            connectionManager.releaseConnection();
            System.out.println("Employee " + e.getFirstName() + " Added");
        } catch (SQLException exc) {

        }
    }

    public void delete(int id) {
        try {
            String selectTableSQL = "DELETE FROM employees WHERE emp_no = " + id;
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectTableSQL);
            connectionManager.releaseConnection();
            System.out.println("employee records deleted");
        } catch (SQLException exc) {

        }
    }

    public void update(int id) {
        try {
            String selectTableSQL = "UPDATE employees SET first_name = 'Fred' WHERE emp_no = 6;";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectTableSQL);
            connectionManager.releaseConnection();
            System.out.println("employee name updated");
        } catch (SQLException exc) {

        }
    }


    public ArrayList<Employee> getAll() {
        try {
            String selectTableSQL = "SELECT * from EMPLOYEES";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            connectionManager.releaseConnection();
            return resultSetToEntities(rs);
        }
        catch (SQLException exc) {

        }
        return new ArrayList<>();
    }

    public ArrayList<Employee> findByKeyValue(String key, String value) {
        try {
            String selectTableSQL = "SELECT * from EMPLOYEES WHERE " + key + " = '" + value + "'";
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            connectionManager.releaseConnection();
            return resultSetToEntities(rs);
        } catch (SQLException exc) {

        }
        return new ArrayList<>();
    }

    public ArrayList<Employee> findByKeyValueLike(String key, String value) throws Exception {
        String selectTableSQL = "SELECT * from EMPLOYEES WHERE " + key + " LIKE '%" + value + "%'";
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        connectionManager.releaseConnection();
        return resultSetToEntities(rs);

    }

    public Employee findById(int inputId) {
        try {
            String selectTableSQL = "SELECT * from EMPLOYEES WHERE emp_no = " + inputId;
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);

            if (rs.next()) {
                Integer id = rs.getInt(1);
                Date birthdate = rs.getDate(2);
                String name = rs.getString(3);
                String lastname = rs.getString(4);
                String gender = rs.getString(5);
                Date hiredate = rs.getDate(6);
                connectionManager.releaseConnection();
                return new Employee(id, birthdate, name, lastname, gender, hiredate);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


        private ArrayList<Employee> resultSetToEntities(ResultSet rs){
            try {
                ArrayList<Employee> employees = new ArrayList<Employee>();
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    Date birthdate = rs.getDate(2);
                    String name = rs.getString(3);
                    String lastname = rs.getString(4);
                    String gender = rs.getString(5);
                    Date hiredate = rs.getDate(6);
                    employees.add(new Employee(id, birthdate, name, lastname, gender, hiredate));
                }
                return employees;
            }
            catch (SQLException exc) {

            }
            return new ArrayList<>();
        }

        public static void main(String[] args) throws Exception {

            EmployeeDAO dao = EmployeeDAO.getInstance();
            for (Employee e : dao.getAll())
                System.out.println(e.toString());

//            for (Employee e : dao.findByKeyValue("first_name", "Yolla"))
//                System.out.println(e);

      //System.out.println(dao.findById(10005));

     //       dao.insert(new Employee(6,new Date(), "mac", "be", "M", new Date()));

          //  dao.delete(6);


        }
    }
