package com.example.mscard.service.concrete;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.dao.repository.CardRepository;
import com.example.mscard.model.request.CardRequestDto;
import com.example.mscard.model.response.CardResponseDto;
import com.example.mscard.service.abstraction.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.mscard.mapper.CardMapper.CARD_MAPPER;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;

    public CardEntity createCard(String userId, CardRequestDto requestDto){
        CardEntity newCard = CARD_MAPPER.buildCardEntity(userId, requestDto);
        return cardRepository.save(newCard);
    }

    public List<CardResponseDto> getCardsByUsedId(String userId){
        List<CardResponseDto> listCards = new ArrayList<>();
        listCards.add(CardResponseDto.builder().pan("1").fullName("Rovshan").build());
        listCards.add(CardResponseDto.builder().pan("2").fullName("Kamil").build());
        System.out.println("Cards for userId: " + userId + " are following: " + listCards);
        return listCards;
    }
}
