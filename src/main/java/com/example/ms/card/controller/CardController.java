package com.example.ms.card.controller;

import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.request.CreateCardRequest;
import com.example.ms.card.model.response.CardResponse;
import com.example.ms.card.service.abstraction.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.example.ms.card.model.constants.HeaderConstants.X_USER_ID;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards")
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CardController {
    CardService cardService;

    @PostMapping()
    @ResponseStatus(CREATED)
    public CardResponse createCard(@RequestHeader(name = X_USER_ID) Long userId, @Valid @RequestBody CreateCardRequest request){
        return cardService.createCard(userId, request);
    }

    @GetMapping("/{id}")
    public CardResponse getCardById(@RequestHeader(name = X_USER_ID) Long userId, @PathVariable Long id){
        return cardService.getCardById(userId, id);
    }

    @GetMapping
    public Set<CardResponse> getCardsByUserId(@RequestHeader(name = X_USER_ID) Long userId){
        return cardService.getCardsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCardById(@RequestHeader(name = X_USER_ID) Long userId, @PathVariable Long id){
        cardService.deleteCardById(userId, id);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(NO_CONTENT)
    public void updateCardStatus(@RequestHeader(name = X_USER_ID) Long userId, @PathVariable Long id, @RequestParam CardStatus status){
        cardService.updateCardStatus(userId, id, status);
    }
}
