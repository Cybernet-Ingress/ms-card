package com.example.ms.card.controller

import com.example.ms.card.model.enums.CardBrand
import com.example.ms.card.model.enums.CardType
import com.example.ms.card.model.request.CardRequestDto
import com.example.ms.card.service.abstraction.CardService
import com.example.ms.card.service.concrete.CardServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class CardControllerTest extends Specification{
    def BASE_URL = "/api/v1/cards"
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    MockMvc mockMvc
    CardService cardService
    CardController cardController

    def setup(){
        cardService = Mock(CardServiceImpl)
        cardController = new CardController(cardService)
        mockMvc = standaloneSetup(cardController).setControllerAdvice().build()
    }

    def "Test CreateCard success case" (){
        given:
        def userId = random.nextLong()
        def url = BASE_URL + "/" + userId
        def pan = '1234123412341234'
        def cardHolder = random.nextObject(String)
        def type = random.nextObject(CardType)
        def brand = random.nextObject(CardBrand)
        def requestDto = CardRequestDto.builder()
            .pan(pan)
            .cardHolder(cardHolder)
            .type(type)
            .brand(brand)
            .build()
        def requestJson = """
            {
                "pan": "$pan",
                "cardHolder" : "$cardHolder",
                "type" : "$type",
                "brand" : "$brand"
            }
        """

        when:
        def result = mockMvc.perform (
                post(url)
                .contentType(APPLICATION_JSON)
                .content(requestJson)
        )

        then:
        1 * cardService.createCard(userId, requestDto)
        result.andExpect(status().isCreated())
    }
}
