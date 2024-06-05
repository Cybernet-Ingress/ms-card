package com.example.ms.card.mapper

import com.example.ms.card.dao.entity.CardEntity
import com.example.ms.card.model.request.CardRequestDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER
import static com.example.ms.card.model.enums.CardStatus.ACTIVE

class CardMapperTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildCardEntity"(){
        given:
        def userId = random.nextLong()
        def request = random.nextObject(CardRequestDto)

        when:
        def response = CARD_MAPPER.buildCardEntity(userId, request)

        then:
        request.pan == response.pan
        request.cardHolder == response.cardHolder
        request.pan == request.pan
        response.balance == BigDecimal.ZERO
        request.type == response.type
        request.brand == response.brand
        response.status == ACTIVE
        userId == response.userId
    }

    def "TestToCardResponseDto"(){
        given:
        def entity = random.nextObject(CardEntity)

        when:
        def response = CARD_MAPPER.toCardResponseDto(entity)

        then:
        entity.id == response.id
        entity.pan == response.pan
        entity.cardHolder == response.cardHolder
        entity.balance == response.balance
        entity.type == response.type
        entity.brand == response.brand
        entity.insertDate == response.insertDate
        entity.status == response.status
        entity.updateDate == response.updateDate
        entity.userId == response.userId
    }

    def "TestToGetCardsResponseDto"(){
        given:
        def entity = random.nextObject(CardEntity)

        when:
        def response = CARD_MAPPER.toGetCardsResponseDto(entity)

        then:
        entity.id == response.id
        entity.pan == response.pan
        entity.balance == response.balance
        entity.type == response.type
    }
}
