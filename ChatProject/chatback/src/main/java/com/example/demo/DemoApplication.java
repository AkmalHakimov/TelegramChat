package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.io.IOException;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(DemoApplication.class, args);
    }
}
