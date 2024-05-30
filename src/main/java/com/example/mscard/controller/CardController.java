package com.example.mscard.controller;

import com.example.mscard.model.response.CardResponseDto;
import com.example.mscard.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping("/{userId}")
    @ResponseStatus(CREATED)
    public void createCard(@PathVariable String userId){
        cardService.createCard(userId);
    }

    @GetMapping("/{userId}")
    public List<CardResponseDto> getCardsByUsedId(@PathVariable String userId){
        return cardService.getCardsByUsedId(userId);
    }
}
