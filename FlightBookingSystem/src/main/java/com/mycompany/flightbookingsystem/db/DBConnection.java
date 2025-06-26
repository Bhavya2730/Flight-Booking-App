package com.mycompany.flightbookingsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Flight_booking";
    private static final String USER = "root";  // Change if needed
    private static final String PASSWORD = "";  // Set MySQL password if required

    public static Connection getConnection() {
        Connection con = null;
        try {
            // ✅ Load MySQL JDBC Driver first
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Establish connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();
        }
        return con;
    }
}
