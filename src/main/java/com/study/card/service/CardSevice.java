package com.study.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.card.dtos.CardDTO;
import com.study.card.model.Card;
import com.study.card.model.enums.Status;
import com.study.card.repository.CardRepository;

@Service
public class CardSevice {
    
    @Autowired
    private CardRepository cardRepository;

    //criar novo card
    public Card createCard(CardDTO cardDTO) {

        Card card = new Card();

        card.setName(cardDTO.getName());
        card.setTheme(cardDTO.getTheme());
        card.setText(cardDTO.getText());
        card.setStartDate(cardDTO.getStartDate());
        card.setEndDate(cardDTO.getEndDate());
        card.setStatus(cardDTO.getStatus() != null ? cardDTO.getStatus() : Status.PENDENTE);
        return cardRepository.save(card);
    }


}
