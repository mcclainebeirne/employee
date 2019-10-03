package com.reach.dp.MockDataToXML;

import java.util.ArrayList;

public class Table {
    private String name;
    private ArrayList<Column> columns;

    public Table(String name, ArrayList<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }
}
