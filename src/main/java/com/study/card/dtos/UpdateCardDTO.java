package com.study.card.dtos;

import java.time.LocalDate;

public record UpdateCardDTO(
    String name, 
    String theme, 
    String text, 
    LocalDate endDate
) {
}