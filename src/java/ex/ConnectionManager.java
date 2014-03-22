package ex;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.*;

public class ConnectionManager {

    static Connection con;
    static String url;

    public static Connection getConnection() {

        try {
            String url = "jdbc:odbc:" + "//localhost:8080/MyVibe";
            // assuming "DataSource" is your DataSource name

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            try {
                con = DriverManager.getConnection(url, "keris", "kerisve");

                // assuming your SQL Server's	username is "username"               
                // and password is "password"

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return con;
    }
}