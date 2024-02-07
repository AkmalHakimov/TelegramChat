package com.example.demo.controller;

import com.example.demo.config.Db;
import com.example.demo.entty.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody User user) throws SQLException {
        System.out.println("salom");
        PreparedStatement preparedStatement = Db.getPreparedStatement("SELECT id,password from users where phone = ?");
        preparedStatement.setString(1,user.getPhone());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String id =resultSet.getString(1);
            String password = resultSet.getString(2);
            if(user.getPassword().equals(password)){
                return ResponseEntity.ok(id);
            }else {
                return ResponseEntity.status(401).body("Login yoki parol xato");
            }
        }else {
            return ResponseEntity.status(401).body("chakka");
        }
    }
}
