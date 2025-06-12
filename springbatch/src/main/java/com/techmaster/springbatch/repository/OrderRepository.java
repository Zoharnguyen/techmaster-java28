package com.techmaster.springbatch.repository;

import com.techmaster.springbatch.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
} 