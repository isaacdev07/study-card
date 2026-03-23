package com.study.card.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    // classe user
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    @Column(unique = true)
    @NotNull(message = "O email não pode ser nulo")
    private String email;
    @NotNull(message = "A senha não pode ser nula")
    private String password;
    
}
