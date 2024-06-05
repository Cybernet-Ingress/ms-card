package com.example.ms.card.service.concrete;

import com.example.ms.card.dao.repository.CardRepository;
import com.example.ms.card.exception.NotFoundException;
import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.request.CardRequestDto;
import com.example.ms.card.model.response.CardResponseDto;
import com.example.ms.card.service.abstraction.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_CODE;
import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_MESSAGE;
import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;

    @Override
    public CardResponseDto createCard(Long userId, CardRequestDto requestDto){
        var newCardEntity = CARD_MAPPER.buildCardEntity(userId, requestDto);
        cardRepository.save(newCardEntity);
        return CARD_MAPPER.toCardResponseDto(newCardEntity);
    }

    @Override
    public CardResponseDto getCardById(Long id){
        return cardRepository.findById(id)
                .map(CARD_MAPPER::toCardResponseDto)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND_CODE, String.format(CARD_NOT_FOUND_MESSAGE, id)));
    }

    @Override
    public List<CardResponseDto> getCardsByUsedId(Long userId){
        var listCards = cardRepository.findAllByUserIdAndStatus(userId, CardStatus.ACTIVE);
        return listCards.stream()
                .map(CARD_MAPPER::toCardResponseDto)
                .collect(Collectors.toList());
    }
}
