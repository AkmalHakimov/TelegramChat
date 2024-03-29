package com.example.demo.entity;

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
public class Message {
    private UUID id;
    private UUID toId;
    private UUID fromId;
    private Timestamp createdAt;
    private String text;
    private Boolean active = false;
}
