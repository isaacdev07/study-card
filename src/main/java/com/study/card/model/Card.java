package com.study.card.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.card.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cards")
public class Card {

    @ManyToOne // anotacão para indicar que muitos cards podem pertencer a um usuário
    @JoinColumn(name = "user_id") 
    @JsonIgnore // evita looping infinito durante a serialização JSON
    private User user;
    
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
