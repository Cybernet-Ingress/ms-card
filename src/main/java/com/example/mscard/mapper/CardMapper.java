package com.example.mscard.mapper;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.model.request.CardRequestDto;

import java.math.BigDecimal;

import static com.example.mscard.model.enums.CardStatus.ACTIVE;

public enum CardMapper {
    CARD_MAPPER;

    public CardEntity buildCardEntity(String userId, CardRequestDto requestDto){
        return CardEntity.builder()
                .pan(requestDto.getPan())
                .cardHolder(requestDto.getCardHolder())
                .balance(BigDecimal.ZERO)
                .type(requestDto.getType())
                .brand(requestDto.getBrand())
                .status(ACTIVE)
                .userId(Long.valueOf(userId))
                .build();
    }
}
