package com.example.mscard.controller;

import com.example.mscard.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

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
}
