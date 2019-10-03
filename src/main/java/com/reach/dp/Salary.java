package com.reach.dp;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.Date;

public class Salary {
    private int id;
    private int salary;
    private Date fromDate;
    private Date toDate;

    public Salary(int id, int salary, Date fromDate, Date toDate) {
        this.id = id;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    // GSON library for JSON
    @Override
    public String toString(){
        return new com.google.gson.Gson().toJson(this);
    }
}
