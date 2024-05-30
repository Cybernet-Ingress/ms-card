package com.example.mscard.service.concrete;

import com.example.mscard.service.abstraction.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    public void createCard(String userId){
        System.out.println("Card successfully created for userId: " + userId);
    }
}
