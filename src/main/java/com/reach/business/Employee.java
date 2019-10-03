package com.reach.business;

import java.util.Date;


public class Employee {
    private int id;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hire_date;

    public Employee(int id, Date birthDate, String firstName, String lastName, String gender, Date hire_date) {
        this.id = id;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hire_date = hire_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    // GSON library for JSON
    @Override
   public String toString(){
        return new com.google.gson.Gson().toJson(this);
   }


//    @Override
//    public String toString() {
//        return "\"employee\"[\n" +
//                " {\"id\" : '\"" + id +
//                "\"},\n {\"birthDate\" : \"" + birthDate +
//                "\"},\n {\"firstName\" : \"" + firstName +"\"" +
//                "},\n {\"lastName\" : \"" + lastName +"\"" +
//                "},\n {\"gender\" : \"" + gender +"\""+
//                "},\n {\"hire_date\" : \"" + hire_date +
//                "\"}\n]\n";
//    }
}
