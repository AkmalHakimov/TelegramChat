package com.example.demo.service.UserService;

import org.springframework.http.HttpEntity;

public interface UserService {
    HttpEntity<?> getUsers(String key);
}
