package com.example.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Db {
    private  static final Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chatdb",
                    "postgres",
                    "root1234"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
}
