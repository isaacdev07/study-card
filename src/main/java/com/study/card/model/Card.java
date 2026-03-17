package com.study.card.model;

import java.time.LocalDate;

import com.study.card.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cards")
public class Card {
    
    // classe card
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String theme;
    private String text;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

}
