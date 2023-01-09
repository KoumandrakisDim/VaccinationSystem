package com.Vaccination.Service;

import java.sql.*;

public class Database {


    public static ResultSet getQueryResults(String query) throws ClassNotFoundException, SQLException {
        Connection conn = connectToDatabase();
        Statement st = conn.createStatement();
        return st.executeQuery(query);
    }

    public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/vaccination";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        return conn;
    }
}
