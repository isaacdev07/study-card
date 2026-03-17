package com.study.card.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
        card.setStartDate(LocalDate.now());// seta a data de inicio como a data atual
        card.setEndDate(cardDTO.getEndDate());
        card.setStatus(cardDTO.getStatus() != null ? cardDTO.getStatus() : Status.PENDENTE);
        return cardRepository.save(card);
    }

    // setar card como concluído
    public Card completeCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card não encontrado!"));
        card.setStatus(Status.CONCLUIDO);
        return cardRepository.save(card);
    }

    // método para verificar cards expirados todos os dias à meia-noite
    @Scheduled(cron = "0 0 0 * * ?") 
    public void verifyExpiredCards() {
        LocalDate today = LocalDate.now();
        
        // busca apenas os cards que estão pendentes e que ja passou a data de expiração
        List<Card> cardsAtrasados = cardRepository.findByStatusAndEndDateBefore(Status.PENDENTE, today);

        // se a lista estiver vazia, não precisa fazer nada, mas se tiver cards, vamos marcar como expirados
        for (Card card : cardsAtrasados) {
            card.setStatus(Status.EXPIRADO);
            System.out.println("Card ID " + card.getId() + " expirou!");
        }

        // salva as alterações no banco de dados
        if (!cardsAtrasados.isEmpty()) {
            cardRepository.saveAll(cardsAtrasados);
        }
    }

    // get todos os cards
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

}
