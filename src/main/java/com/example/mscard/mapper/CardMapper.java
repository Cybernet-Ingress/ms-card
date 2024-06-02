package com.example.mscard.mapper;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.model.request.CardRequestDto;

import static com.example.mscard.model.enums.CardStatus.ACTIVE;

public enum CardMapper {
    CARD_MAPPER;

    public CardEntity buildCardEntity(String userId, CardRequestDto requestDto){
        return CardEntity.builder()
                //.id()
                .pan(requestDto.getPan())
                .cardHolder(requestDto.getCardHolder())
                .status(ACTIVE)
                .userId(Long.valueOf(userId))
                .build();
    }
}
