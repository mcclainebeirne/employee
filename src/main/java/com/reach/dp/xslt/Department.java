package com.reach.dp.xslt;

public class Department implements java.io.Serializable {
    private char deptNo;
    private String deptName;

    public Department(char deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public char getdeptNo() {
        return this.deptNo;
    }

    public void setdeptNo(char deptNo) {
        this.deptNo = deptNo;
    }

    public String getdeptName() {
        return this.deptName;
    }

    public void setdeptName(String deptName) {
        this.deptName = deptName;
    }

    // GSON library for JSON
    // normal output: {"deptNo":"1","deptName":"mac"}
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "[\n\t" + new com.google.gson.Gson().toJson(this) + "\n]";
    }


}