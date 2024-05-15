package com.example.demo.service.AuthService;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    @Override
    public HttpEntity<?> login(User user) {
        Optional optional = userRepo.checkIfUserCanAccess(user.getPhone(), user.getPassword());
        if(optional.isEmpty()){
        return ResponseEntity.status(401).body("Login yoki parol xato");
        }
        return ResponseEntity.ok(optional.get());
    }
}
