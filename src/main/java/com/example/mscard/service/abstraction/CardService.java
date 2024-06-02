package com.example.mscard.service.abstraction;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.model.request.CardRequestDto;
import com.example.mscard.model.response.CardResponseDto;

import java.util.List;

public interface CardService {
    CardEntity createCard(String userId, CardRequestDto requestDto);

    List<CardResponseDto> getCardsByUsedId(String userId);
}
