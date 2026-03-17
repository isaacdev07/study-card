package com.study.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.card.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    
}
