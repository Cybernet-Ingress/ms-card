package com.example.ms.card.service.abstraction;

import com.example.ms.card.model.request.CardRequestDto;
import com.example.ms.card.model.response.CardResponseDto;

import java.util.List;

public interface CardService {
    CardResponseDto createCard(Long userId, CardRequestDto requestDto);

    CardResponseDto getCardById(Long id);

    List<CardResponseDto> getCardsByUsedId(Long userId);
}
