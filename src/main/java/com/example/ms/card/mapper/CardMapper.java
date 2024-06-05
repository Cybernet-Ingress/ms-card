package com.example.ms.card.mapper;

import com.example.ms.card.dao.entity.CardEntity;
import com.example.ms.card.model.request.CardRequestDto;
import com.example.ms.card.model.response.CardResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.ms.card.model.enums.CardStatus.ACTIVE;

public enum CardMapper {
    CARD_MAPPER;

    public CardEntity buildCardEntity(Long userId, CardRequestDto requestDto){
        return CardEntity.builder()
                .pan(requestDto.getPan())
                .cardHolder(requestDto.getCardHolder())
                .balance(BigDecimal.ZERO)
                .type(requestDto.getType())
                .brand(requestDto.getBrand())
                .insertDate(LocalDateTime.now())
                .status(ACTIVE)
                .updateDate(LocalDateTime.now())
                .userId(userId)
                .build();
    }

    public CardResponseDto toCardResponseDto(CardEntity entity){
        return CardResponseDto.builder()
                .id(entity.getId())
                .pan(entity.getPan())
                .cardHolder(entity.getCardHolder())
                .balance(entity.getBalance())
                .type(entity.getType())
                .brand(entity.getBrand())
                .insertDate(entity.getInsertDate())
                .status(entity.getStatus())
                .updateDate(entity.getUpdateDate())
                .userId(entity.getUserId())
                .build();
    }
}
