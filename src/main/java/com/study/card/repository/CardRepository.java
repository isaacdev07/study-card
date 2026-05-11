package com.study.card.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.card.model.Card;
import com.study.card.model.User;
import com.study.card.model.enums.Status;

public interface CardRepository extends JpaRepository<Card, Long> {
    
    // método para encontrar cards expirados
    List<Card> findByStatusAndEndDateBefore(Status status, LocalDate data);

    List<Card> findByUser(User user);

}
