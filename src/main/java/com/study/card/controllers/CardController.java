package com.study.card.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.card.dtos.CardDTO;
import com.study.card.model.Card;
import com.study.card.service.CardSevice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardSevice cardSevice;

    // criar novo card
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody CardDTO cardDTO) {
        
        Card savedCard = cardSevice.createCard(cardDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }

    // marcar card como concluído
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Card> markAsCompleted(@PathVariable Long id) {
        Card updatedCard = cardSevice.completeCard(id);
        return ResponseEntity.ok(updatedCard);
    }
    
    // get todos os cards
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardSevice.getAllCards();
        
        // Retorna a lista de cards com o status 200 OK
        return ResponseEntity.ok(cards);
    }
    
}
