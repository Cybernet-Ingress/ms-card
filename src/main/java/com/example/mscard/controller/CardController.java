package com.example.mscard.controller;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.model.request.CardRequestDto;
import com.example.mscard.model.response.CardResponseDto;
import com.example.mscard.service.abstraction.CardService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardController {
    CardService cardService;

    /*
    URL: http://localhost:8080/api/v1/cards/2
    {
    "pan":"1234567812345678",
    "cardHolder" : "Alim Qasimov",
    "type" : "DEBIT",
    "brand" : "VISA"
    }
     */
    @PostMapping("/{userId}")
    @ResponseStatus(CREATED)
    public CardEntity createCard(@PathVariable Long userId, @Valid @RequestBody CardRequestDto requestDto){
        return cardService.createCard(userId, requestDto);
    }

    // http://localhost:8080/api/v1/cards/1
    @GetMapping("/{userId}")
    public List<CardResponseDto> getCardsByUsedId(@PathVariable Long userId){
        return cardService.getCardsByUsedId(userId);
    }
}
