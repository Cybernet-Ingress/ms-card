package com.example.ms.card.service

import com.example.ms.card.client.UserClient
import com.example.ms.card.client.UserClientMock
import com.example.ms.card.dao.entity.CardEntity
import com.example.ms.card.dao.repository.CardRepository
import com.example.ms.card.model.request.CardRequestDto
import com.example.ms.card.service.abstraction.CardService
import com.example.ms.card.service.concrete.CardServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER
import static com.example.ms.card.model.enums.CardStatus.ACTIVE

class CardServiceImplTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    CardService cardService
    CardRepository cardRepository
    UserClient userClient

    def setup(){
        cardRepository = Mock()
        userClient = new UserClientMock()
        cardService = new CardServiceImpl(cardRepository, userClient)
    }

    def "TestCreateCard success case" (){
        given:
        def userId = random.nextLong()
        def requestDto = random.nextObject(CardRequestDto)
        def entity = CARD_MAPPER.buildCardEntity(userId, requestDto)
        def expected = CARD_MAPPER.toCardResponseDto(entity)

        when:
        def actual = cardService.createCard(userId, requestDto)

        then:
        1 * cardRepository.save(entity)
        actual.id == expected.id
        actual.pan == expected.pan
        actual.cardHolder == expected.cardHolder
        actual.balance == expected.balance
        actual.type == expected.type
        actual.brand == expected.brand
        actual.status == expected.status
        actual.userId == expected.userId
    }


    def "TestGetCardById success case" (){
        given:
        def id = random.nextLong()
        def entity = random.nextObject(CardEntity)
        def expected = CARD_MAPPER.toCardResponseDto(entity)

        when:
        def actual = cardService.getCardById(id)

        then:
        1 * cardRepository.findByIdAndStatus(id, ACTIVE) >> Optional.of(entity)
        actual == expected
    }

    /*
    def "TestGetCardById error case when card not found" (){
        given:

        when:
        cardService.getCardById(id)

        then:
    }*/
}
