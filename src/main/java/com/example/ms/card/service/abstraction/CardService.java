package com.example.ms.card.service.abstraction;

import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.request.CreateCardRequest;
import com.example.ms.card.model.response.CardResponse;

import java.util.Set;

public interface CardService {
    CardResponse createCard(Long userId, CreateCardRequest request);

    CardResponse getCardById(Long id);

    CardResponse getCardById(Long userId, Long id);

    Set<CardResponse> getCardsByUserId(Long userId);

    Set<CardResponse> getAllCardsByUserId(Long userId);

    void deleteCardById(Long userId, Long id);

    void updateCardStatus(Long id, CardStatus status);

    void updateCardStatus(Long userId, Long id, CardStatus status);

    void deleteCacheByUserId(Long userId);
}
