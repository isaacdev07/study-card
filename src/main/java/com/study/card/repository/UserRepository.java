package com.study.card.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.card.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

}
