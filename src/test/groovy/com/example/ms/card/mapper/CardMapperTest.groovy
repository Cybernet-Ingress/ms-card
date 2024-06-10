package com.example.ms.card.mapper

import com.example.ms.card.dao.entity.CardEntity
import com.example.ms.card.model.request.CreateCardRequest
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
        def request = random.nextObject(CreateCardRequest)

        when:
        def response = CARD_MAPPER.buildCardEntity(userId, request)

        then:
        request.pan == response.pan
        request.cardHolder == response.cardHolder
        BigDecimal.ZERO == response.balance
        request.type == response.type
        request.brand == response.brand
        null != response.createdAt
        ACTIVE == response.status
        null != response.updatedAt
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
        entity.createdAt == response.createdAt
        entity.status == response.status
        entity.updatedAt == response.updatedAt
        entity.userId == response.userId
    }
}
