package com.study.card.dtos;

import java.time.LocalDate;

import com.study.card.model.enums.Status;

import lombok.Data;

@Data
public class CardDTO {

    private String name;
    private String theme;
    private String text;
    //private LocalDate startDate; a data é definida automaticamente
    private LocalDate endDate;
    private Status status;

    
}
