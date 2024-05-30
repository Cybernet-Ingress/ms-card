package com.example.mscard.service.abstraction;

import com.example.mscard.model.response.CardResponseDto;

import java.util.List;

public interface CardService {
    void createCard(String userId);

    List<CardResponseDto> getCardsByUsedId(String userId);
}
