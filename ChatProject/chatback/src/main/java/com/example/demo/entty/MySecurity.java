package com.example.demo.entty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySecurity {
    private String role;
    private String url;
    private String method;
}
