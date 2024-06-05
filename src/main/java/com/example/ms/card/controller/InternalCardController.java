package com.example.ms.card.controller;

import com.example.ms.card.model.request.CardRequestDto;
import com.example.ms.card.model.response.CardResponseDto;
import com.example.ms.card.service.abstraction.CardService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal/api/v1/cards")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InternalCardController {
    CardService cardService;

    @PostMapping("/{userId}")
    @ResponseStatus(CREATED)
    public CardResponseDto createCard(@PathVariable Long userId, @Valid @RequestBody CardRequestDto requestDto){
        return cardService.createCard(userId, requestDto);
    }

    @GetMapping("/{id}")
    public CardResponseDto getCardById(@PathVariable Long id){
        return cardService.getCardById(id);
    }

    @GetMapping("/user/{userId}")
    public List<CardResponseDto> getCardsByUserId(@PathVariable Long userId){
        return cardService.getCardsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCardById(@PathVariable Long id){
        cardService.deleteCardById(id);
    }

    @PatchMapping("/stolen/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStolenCard(@PathVariable Long id){
        cardService.updateStolenCard(id);
    }

    @PatchMapping("/block/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateBlockedCard(@PathVariable Long id){
        cardService.updateBlockedCard(id);
    }
}
