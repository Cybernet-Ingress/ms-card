package com.example.ms.card.service.concrete;

import com.example.ms.card.aspect.annotation.LogDto;
import com.example.ms.card.aspect.annotation.LogExecutionTime;
import com.example.ms.card.client.UserClient;
import com.example.ms.card.dao.entity.CardEntity;
import com.example.ms.card.dao.repository.CardRepository;
import com.example.ms.card.exception.NotFoundException;
import com.example.ms.card.model.request.CardRequestDto;
import com.example.ms.card.model.response.CardResponseDto;
import com.example.ms.card.service.abstraction.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_CODE;
import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_MESSAGE;
import static com.example.ms.card.exception.ExceptionConstants.USER_NOT_FOUND_CODE;
import static com.example.ms.card.exception.ExceptionConstants.USER_NOT_FOUND_MESSAGE;
import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER;
import static com.example.ms.card.model.enums.CardStatus.ACTIVE;
import static com.example.ms.card.model.enums.CardStatus.DELETED;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;
    UserClient userClient;

    @LogDto
    @Override
    public CardResponseDto createCard(Long userId, CardRequestDto requestDto){
        if(!userClient.getUser(userId)){
            throw new NotFoundException(USER_NOT_FOUND_CODE, String.format(USER_NOT_FOUND_MESSAGE, userId));
        }

        var newCardEntity = CARD_MAPPER.buildCardEntity(userId, requestDto);
        cardRepository.save(newCardEntity);
        return CARD_MAPPER.toCardResponseDto(newCardEntity);
    }

    @LogDto
    @Override
    public CardResponseDto getCardById(Long id){
        var cardEntity = fetchCardIfExists(id);
        return CARD_MAPPER.toCardResponseDto(cardEntity);
    }

    @LogDto
    @LogExecutionTime
    @Override
    public List<CardResponseDto> getCardsByUsedId(Long userId){
        if(!userClient.getUser(userId)){
            throw new NotFoundException(USER_NOT_FOUND_CODE, String.format(USER_NOT_FOUND_MESSAGE, userId));
        }

        var listCards = cardRepository.findAllByUserIdAndStatus(userId, ACTIVE);
        return listCards.stream()
                .map(CARD_MAPPER::toCardResponseDto)
                .collect(Collectors.toList());
    }

    @LogDto
    @Override
    public void deleteCardById(Long id){
        var cardEntity = fetchCardIfExists(id);
        cardEntity.setStatus(DELETED);
        cardEntity.setUpdateDate(LocalDateTime.now());
        cardRepository.save(cardEntity);
    }

    private CardEntity fetchCardIfExists(Long id){
        return cardRepository.findByIdAndStatus(id, ACTIVE)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND_CODE, String.format(CARD_NOT_FOUND_MESSAGE, id)));
    }
}
