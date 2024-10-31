package com.example.ms.card.controller;

import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.response.CardResponse;
import com.example.ms.card.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/v1/cards")
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class InternalCardController {
    CardService cardService;

    @GetMapping("/{id}")
    public CardResponse getCardById(@PathVariable Long id){
        return cardService.getCardById(id);
    }

    @GetMapping("/user/{userId}")
    public Set<CardResponse> getAllCardsByUserId(@PathVariable Long userId){
        return cardService.getAllCardsByUserId(userId);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(NO_CONTENT)
    public void updateCardStatus(@PathVariable Long id, @RequestParam CardStatus status){
        cardService.updateCardStatus(id, status);
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCacheByUserId(@PathVariable Long userId){
        cardService.deleteCacheByUserId(userId);
    }
}
