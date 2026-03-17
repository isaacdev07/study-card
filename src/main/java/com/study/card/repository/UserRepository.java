package com.study.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.card.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
