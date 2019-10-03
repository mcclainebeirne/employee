package com.reach.dp.MockDataToXML;

import java.util.ArrayList;

public class Database {

    private String name;
    private ArrayList tables;

    public Database(String name, ArrayList tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public ArrayList getTables() {
        return tables;
    }
}
