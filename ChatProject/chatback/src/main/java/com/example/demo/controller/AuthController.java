package com.example.demo.controller;

import com.example.demo.config.Db;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody User user) throws SQLException {
        return authService.login(user);
    }

}
