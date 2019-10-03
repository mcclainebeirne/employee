package com.reach.business;

import com.reach.data.EmployeeDAO;
import netscape.javascript.JSObject;
import com.google.gson.Gson;
import java.util.ArrayList;

public class EmployeeCTL {

    private static EmployeeDAO dao = EmployeeDAO.getInstance();

//    public static void insert(Employee e) {
//    }
//
//    public static void delete(int id) {
//    }
//
//    public static void update(int id) {
//
//    }
//
//
    public static ArrayList<Employee> getAll() {
        return dao.getAll();
     }

    public static ArrayList<Employee> findByName(String name) {
        return dao.findByKeyValue("first_name", name);
    }
//    public static String findByName(String name, String format) {
//        return jsonT.toJson(dao.findByKeyValue("first_name", name));
//    }
//
//
//    public static String findByLastname(String lastname) {
//        return jsonT.toJson(dao.findByKeyValue("last_name", lastname));
//    }


//    public static ArrayList<Employee> findByKeyValueLike(String key, String value) throws Exception {
//    }
//
//    public static Employee findById(int inputId) {
//  }
//

//    public static String toJSON(ArrayList<Employee> employees) {
//        return jsonT.toJson(employees);
//    }

}
