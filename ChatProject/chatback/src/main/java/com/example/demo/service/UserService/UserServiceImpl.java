package com.example.demo.service.UserService;

import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public HttpEntity<?> getUsers(String key) {
        return ResponseEntity.ok(userRepo.getUsers(UUID.fromString(key)));
    }
}
