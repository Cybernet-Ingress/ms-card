package com.example.ms.card.service

import com.example.ms.card.client.UserClient
import com.example.ms.card.client.UserClientMock
import com.example.ms.card.dao.repository.CardRepository
import com.example.ms.card.model.request.CardRequestDto
import com.example.ms.card.service.abstraction.CardService
import com.example.ms.card.service.concrete.CardServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER

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
        def responseDto = CARD_MAPPER.toCardResponseDto(entity)

        when:
        cardService.createCard(userId, requestDto)

        then:
        1 * cardRepository.save(entity)
        responseDto.id == entity.id
        responseDto.pan == entity.pan
        responseDto.cardHolder == entity.cardHolder
        responseDto.balance == entity.balance
        responseDto.type == entity.type
        responseDto.brand == entity.brand
        responseDto.insertDate == entity.insertDate
        responseDto.status == entity.status
        responseDto.updateDate == entity.updateDate
        responseDto.userId == entity.userId
    }

    /*
    def "TestGetCardById success case" (){
        given:
        def id = random.nextLong()

        when:
        cardService.getCardById(id)

        then:
    }*/

    /*
    def "TestGetCardById error case when card not found" (){
        given:

        when:
        cardService.getCardById(id)

        then:
    }*/
}
