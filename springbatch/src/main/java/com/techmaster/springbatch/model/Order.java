package com.techmaster.springbatch.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "app_orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String quantity;
    private String price;

} 