package com.reach.business;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class EmployeesFacade {

    public enum OutputFormats {JSON, XML, ATOM, YAML, CSV}

    private static Gson jsonFormatter = new Gson();
    private static XMLTransformer transformer=XMLTransformer.getInstance();
    private static YamlConverter yamlFormatter = new YamlConverter();

    private static String formatter(Object employees, OutputFormats format) throws IOException {
        switch (format) {
            case JSON:
                return jsonFormatter.toJson(employees);
            case XML:
                return transformer.toXMLString(employees);
            case ATOM:
            case YAML:
                return  yamlFormatter.asYaml(jsonFormatter.toJson(employees));
            case CSV:
               // formatToCSV(employees)
                break;
        }
        return "";
    }

    public static String getAllEmployees(OutputFormats format) throws IOException {
        return formatter(EmployeeCTL.getAll(), format);
    }

    public static String findEmployeesByName(String name, OutputFormats format)  throws IOException {
        return formatter(EmployeeCTL.findByName(name), format);
    }

    public static String formatToCSV(ArrayList<Employee> employees) {

       String herders = "id, birthDate, firstName, lastName, gender, hireDate\n";
        String s = "";
        for (Employee e : employees) {
            s +=  e.getId() + ", ";
            s += e.getBirthDate() + ", \t";
            s += e.getFirstName() + ", \t";
            s += e.getLastName() + ",  \t";
            s += e.getGender() + ", \t\t";
            s += e.getHire_date() + ", \n";
        }
       return herders + s;

    }

    public static void main(String[] args) throws IOException {
//        System.out.println(EmployeesFacade.getAllEmployees(OutputFormats.JSON));
        System.out.println(EmployeesFacade.findEmployeesByName("mac", OutputFormats.YAML));
//        System.out.println(formatToCSV(EmployeeCTL.findByName("mac")));


    }

}
