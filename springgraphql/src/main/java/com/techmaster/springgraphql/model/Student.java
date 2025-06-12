package com.techmaster.springgraphql.model;

import lombok.Data;

@Data
public class Student {
    private String id;
    private String name;
    private String email;
    private Integer age;
} 