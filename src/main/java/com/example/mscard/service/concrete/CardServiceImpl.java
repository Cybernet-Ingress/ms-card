package com.example.mscard.service.concrete;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.dao.repository.CardRepository;
import com.example.mscard.model.enums.CardStatus;
import com.example.mscard.model.request.CardRequestDto;
import com.example.mscard.model.response.CardResponseDto;
import com.example.mscard.service.abstraction.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mscard.mapper.CardMapper.CARD_MAPPER;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;

    @Override
    public CardEntity createCard(Long userId, CardRequestDto requestDto){
        CardEntity newCard = CARD_MAPPER.buildCardEntity(userId, requestDto);
        return cardRepository.save(newCard);
    }

    @Override
    public List<CardResponseDto> getCardsByUsedId(Long userId){
        List<CardEntity> allByUserId = cardRepository.findAllByUserIdAndStatus(userId, CardStatus.ACTIVE);
        return allByUserId.stream()
                .map(CARD_MAPPER::toCardRequestDto)
                .collect(Collectors.toList());
    }
}
