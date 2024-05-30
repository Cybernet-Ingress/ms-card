package com.example.mscard.service.concrete;

import com.example.mscard.model.response.CardResponseDto;
import com.example.mscard.service.abstraction.CardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    public void createCard(String userId){
        System.out.println("Card successfully created for userId: " + userId);
    }

    public List<CardResponseDto> getCardsByUsedId(String userId){
        List<CardResponseDto> listCards = new ArrayList<>();
        listCards.add(CardResponseDto.builder().pan("1").fullName("Rovshan").build());
        listCards.add(CardResponseDto.builder().pan("2").fullName("Kamil").build());
        System.out.println("Cards for userId: " + userId + " are following: " + listCards);
        return listCards;
    }
}
