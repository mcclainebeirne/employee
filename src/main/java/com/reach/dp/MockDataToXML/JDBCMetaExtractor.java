package com.reach.dp.MockDataToXML;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.util.HashMap;

public class JDBCMetaExtractor {

        public static HashMap<Integer,String> initDataTypes() {
            HashMap<Integer,String> types = new HashMap<Integer, String>();
            types.put(-7,"bit");
            types.put(16, "Boolean");
            types.put(-6, "Integer");
            types.put(-5, "Integer");
            types.put(-4, "binary");
            types.put(-3, "binary");
            types.put(-2, "binary");
            types.put(-1, "String");
            types.put(1, "Char");
            types.put(3, "Double");
            types.put(4, "Integer");
            types.put(5, "Integer");
            types.put(7, "Float");
            types.put(8, "Double");
            types.put(12, "String");
            types.put(91, "Date");
            types.put(92, "Date");
            types.put(93, "Date");
            return types;
        }


        public static void extractMetadata(DatabaseMetaData dbMeta, String dbName) throws Exception {
            HashMap<Integer,String> types = initDataTypes();
            ResultSet rs = dbMeta.getTables(dbName, null, null, new String[]{"TABLE"});
            System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            System.out.println("<database name=\""+ dbName + "\">");
            while (rs.next()) {
                String tableName = rs.getString(3);
                System.out.println("\t<table name=\"" + tableName + "\">");
                ResultSet trs = dbMeta.getColumns(null, null, tableName, null);
                ResultSet rspk = dbMeta.getPrimaryKeys(null,null, tableName);
                while( rspk.next( ) ) {
                    String pkey = rspk.getString("COLUMN_NAME");

                    while (trs.next()) {
                        String columnName = trs.getString("COLUMN_NAME");
                        Integer dataType = trs.getInt("DATA_TYPE");
                        String columnSize = trs.getString("COLUMN_SIZE");
                        String decimals = trs.getString("DECIMAL_DIGITS");
                        Boolean isNullable = trs.getString("IS_NULLABLE").equals("YES");
                        Boolean isAutoincrement = trs.getString("IS_AUTOINCREMENT").equals("YES");
                        String s = "\t\t<column ";
                        s += "name=\"" + columnName + "\" ";
                        s += "type=\"" + types.get(dataType) + "\" ";
                        if(columnName.equals(pkey)){
                            s += "pk = \"true\"";
                        }
                        if (isAutoincrement == true) s += "autoincrement=\"true\" ";
                        if (isNullable == true) s += "nullable=\"true\" ";
                        s += "/>";
                        System.out.println(s);

                    }
                }
                System.out.println("\t</table>");
            }
            System.out.println("</database>");
        }

        public static void main(String[] args) {
            try {
                // 1. Load JDBC Driver for MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 2. Connect to the database (use user and password if necessary
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                extractMetadata(conn.getMetaData(), "employees");

            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
            }


        }
    }

