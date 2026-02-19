package com.vander.sistema_bancario.controller;

import com.vander.sistema_bancario.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/requestCard")
    public ResponseEntity requestCard() {
        cardService.requestCard();
        return ResponseEntity.ok("Cartão solicitado com sucesso.");
    }
}
