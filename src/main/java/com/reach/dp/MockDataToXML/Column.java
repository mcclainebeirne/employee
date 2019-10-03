package com.reach.dp.MockDataToXML;

public class Column {

    private String name;
    private boolean pk;
    private String type;

  //  private ArrayList<Employee> employees;

    public Column(String name, boolean pk, String type) {
        this.name = name;
        this.pk = pk;
        this.type = type;
        //this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isPk() {
        return pk;
    }


    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pk=" + pk +
                '}';
    }

}
