package com.techmaster.springbatch.repository;

import com.techmaster.springbatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
} 