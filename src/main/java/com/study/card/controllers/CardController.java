package com.study.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.card.dtos.CardDTO;
import com.study.card.model.Card;
import com.study.card.service.CardSevice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardSevice cardSevice;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody CardDTO cardDTO) {
        
        Card savedCard = cardSevice.createCard(cardDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }
    
    
}
