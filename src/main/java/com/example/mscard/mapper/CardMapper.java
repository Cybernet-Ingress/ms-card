package com.example.mscard.mapper;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.model.request.CardRequestDto;
import com.example.mscard.model.response.CardResponseDto;

import java.math.BigDecimal;

import static com.example.mscard.model.enums.CardStatus.ACTIVE;

public enum CardMapper {
    CARD_MAPPER;

    public CardEntity buildCardEntity(Long userId, CardRequestDto requestDto){
        return CardEntity.builder()
                .pan(requestDto.getPan())
                .cardHolder(requestDto.getCardHolder())
                .balance(BigDecimal.ZERO)
                .type(requestDto.getType())
                .brand(requestDto.getBrand())
                .status(ACTIVE)
                .userId(userId)
                .build();
    }

    public CardResponseDto toCardRequestDto(CardEntity entity){
        return CardResponseDto.builder()
                .id(entity.getId())
                .pan(entity.getPan())
                .cardHolder(entity.getCardHolder())
                .balance(entity.getBalance())
                .type(entity.getType())
                .brand(entity.getBrand())
                .status(entity.getStatus())
                .userId(entity.getUserId())
                .build();
    }
}
