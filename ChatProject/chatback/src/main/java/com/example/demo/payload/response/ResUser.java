package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResUser {
    private UUID id;
    private String firstName,lastName,phone,lastText;
    private Integer count;
    private Timestamp createdAt;
}
