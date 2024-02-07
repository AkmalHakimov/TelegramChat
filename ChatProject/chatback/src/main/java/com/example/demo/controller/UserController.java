package com.example.demo.controller;

import com.example.demo.config.Db;
import com.example.demo.entty.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/me")
    public HttpEntity<?> getUserMe(@RequestHeader(defaultValue = "") String token) throws SQLException {
        if(!token.isEmpty()){
            PreparedStatement preparedStatement = Db.getPreparedStatement("select * from users where id");

        }else{
            return ResponseEntity.ok(new User());
        }
        return null;
    }
}
