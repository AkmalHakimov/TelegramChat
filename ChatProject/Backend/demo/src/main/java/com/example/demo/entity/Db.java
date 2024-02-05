package com.example.demo.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Db {

    public static Connection connection = null;

    static {
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:5432/chatdb", "postresql", "root1234");
            connection.close();
        } catch (SQLException e) {
            // Handle any potential SQL exceptions
            e.printStackTrace();
        }
    }

    public static PreparedStatement gtPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }




}
