package com.example.ms.card.service

import com.example.ms.card.dao.entity.CardEntity
import com.example.ms.card.dao.repository.CardRepository
import com.example.ms.card.exception.NotFoundException
import com.example.ms.card.model.request.CreateCardRequest
import com.example.ms.card.service.abstraction.CacheService
import com.example.ms.card.service.abstraction.CardService
import com.example.ms.card.service.concrete.CardServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_CODE
import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER
import static com.example.ms.card.model.enums.CardStatus.ACTIVE

class CardServiceHandlerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    CardService cardService
    CardRepository cardRepository
    CacheService cacheService

    def setup(){
        cardRepository = Mock()
        cacheService = Mock()
        cardService = new CardServiceHandler(cardRepository, cacheService)
    }

    def "TestCreateCard success case" (){
        given:
        def userId = random.nextLong()
        def request = random.nextObject(CreateCardRequest)
        def entity = CARD_MAPPER.buildCardEntity(userId, request)
        def expected = random.nextObject(CardEntity)

        when:
        def actual = cardService.createCard(userId, request)

        then:
        1 * cardRepository.save(entity)
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

    def "TestGetCardById error case when card not found" (){
        given:
        def id = random.nextLong()

        when:
        cardService.getCardById(id)

        then:
        1 * cardRepository.findByIdAndStatus(id, ACTIVE) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.code == CARD_NOT_FOUND_CODE
    }

    def "TestGetCardsByUserId success case" (){
        given:
        def userId = random.nextLong()
        //def expectedList = random.nextObject(List<GetCardsResponseDto>)
        def expected = expectedList[0]

        when:
        def actual = cardService.getCardsByUserId(userId)

        then:
        userClient.userExists(userId)
        //1 * cardRepository.findAllByUserIdAndStatus(userId, ACTIVE) >> List<GetCardsResponseDto>.of()
        actual.size() == expectedList.size()
        actual.size() == 0
        //actual[0].id == expected.id
        //actual[0].pan == expected.pan
        //actual[0].balance == expected.balance
        //actual[0].type == expected.type
    }
}
