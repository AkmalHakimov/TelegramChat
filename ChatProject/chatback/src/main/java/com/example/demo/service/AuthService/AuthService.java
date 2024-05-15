package com.example.demo.service.AuthService;

import com.example.demo.entity.User;
import org.springframework.http.HttpEntity;

public interface AuthService {


    HttpEntity<?> login(User user);
}
