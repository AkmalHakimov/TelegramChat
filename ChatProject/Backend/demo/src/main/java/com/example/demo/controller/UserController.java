package com.example.demo.controller;

import com.example.demo.entity.Db;
import com.example.demo.entity.User;
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
            PreparedStatement preparedStatement = Db.gtPreparedStatement("select * from users where id");

        }else{
            return ResponseEntity.ok(new User());
        }
        return null;
    }

    @GetMapping
    public HttpEntity<?> getUsers(){
        
    }
}
