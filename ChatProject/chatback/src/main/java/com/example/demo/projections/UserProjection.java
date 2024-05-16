package com.example.demo.projections;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.UUID;

public interface UserProjection {

    UUID getId();

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.last_name}")
    String getLastName();

    @Value("#{target.last_text}")
    String getLastText();

    @Value("#{target.phone}")
    String getPhone();

    Integer getCount();

    @Value("#{target.last_time}")
    Timestamp getCreatedAt();
}
