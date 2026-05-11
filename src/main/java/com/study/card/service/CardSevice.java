package com.study.card.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder; // Novo Import!
import org.springframework.stereotype.Service;

import com.study.card.dtos.CardDTO;
import com.study.card.dtos.UpdateCardDTO;
import com.study.card.model.Card;
import com.study.card.model.User; // Novo Import!
import com.study.card.model.enums.Status;
import com.study.card.repository.CardRepository;

@Service
public class CardSevice {
    
    @Autowired
    private CardRepository cardRepository;

    // criar novo card vinculado ao usuário logado
    public Card createCard(CardDTO cardDTO) {
        
        // pega o user 
        User userLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Card card = new Card();
        card.setName(cardDTO.getName());
        card.setTheme(cardDTO.getTheme());
        card.setText(cardDTO.getText());
        card.setStartDate(LocalDate.now());
        card.setEndDate(cardDTO.getEndDate());
        card.setStatus(cardDTO.getStatus() != null ? cardDTO.getStatus() : Status.PENDENTE);
        
        // vincula o card ao usuário logado
        card.setUser(userLogado); 
        
        return cardRepository.save(card);
    }

    // setar card como concluído validando o dono
    public Card completeCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card não encontrado!"));
                
        // pega qual usuário está logado
        User userLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // valida se o card pertence ao usuário logado
        if (!card.getUser().getId().equals(userLogado.getId())) {
            throw new RuntimeException("Acesso negado: Você não é o dono deste card!");
        }

        card.setStatus(Status.CONCLUIDO);
        return cardRepository.save(card);
    }

    // método para verificar cards expirados 
    @Scheduled(cron = "0 0 0 * * ?") 
    public void verifyExpiredCards() {
        LocalDate today = LocalDate.now();
        
        List<Card> cardsAtrasados = cardRepository.findByStatusAndEndDateBefore(Status.PENDENTE, today);

        for (Card card : cardsAtrasados) {
            card.setStatus(Status.EXPIRADO);
            System.out.println("Card ID " + card.getId() + " expirou!");
        }

        if (!cardsAtrasados.isEmpty()) {
            cardRepository.saveAll(cardsAtrasados);
        }
    }

    // get todos os cards do usuário logado
    public List<Card> getAllCards() {
        User userLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        // *ATENÇÃO*: Você precisa criar esse método no seu CardRepository
        return cardRepository.findByUser(userLogado);
    }

    // atualizar informações do card
    public Card updateCard(Long id, UpdateCardDTO data) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card não encontrado!"));

        // pega o usuário logado
        User userLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // verifica se o card pertence ao usuário logado
        if (!card.getUser().getId().equals(userLogado.getId())) {
            throw new RuntimeException("Acesso negado: Você não pode editar um card de outro usuário!");
        }

        // só atualiza os campos que foram enviados (não nulos)
        if (data.name() != null) card.setName(data.name());
        if (data.theme() != null) card.setTheme(data.theme());
        if (data.text() != null) card.setText(data.text());
        if (data.endDate() != null) card.setEndDate(data.endDate());

        return cardRepository.save(card);
    }
}