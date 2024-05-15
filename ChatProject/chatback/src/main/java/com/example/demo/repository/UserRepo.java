package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    @Query(value = "select id from users where phone = :phone and password = :password",nativeQuery = true)
    Optional checkIfUserCanAccess(String phone, String password);
}
