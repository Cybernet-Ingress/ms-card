package com.example.ms.card.service.concrete;

import com.example.ms.card.aspect.annotation.LogDto;
import com.example.ms.card.aspect.annotation.LogExecutionTime;
import com.example.ms.card.cache.CacheService;
import com.example.ms.card.client.UserClient;
import com.example.ms.card.dao.entity.CardEntity;
import com.example.ms.card.dao.repository.CardRepository;
import com.example.ms.card.exception.NotFoundException;
import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.request.CardRequestDto;
import com.example.ms.card.model.response.CardResponseDto;
import com.example.ms.card.model.response.GetCardsResponseDto;
import com.example.ms.card.service.abstraction.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.ms.card.cache.CacheKeyPrefixConstants.MS_CARD;
import static com.example.ms.card.cache.CacheKeyPrefixConstants.MS_CARD_USER;
import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_CODE;
import static com.example.ms.card.exception.ExceptionConstants.CARD_NOT_FOUND_MESSAGE;
import static com.example.ms.card.exception.ExceptionConstants.USER_NOT_FOUND_CODE;
import static com.example.ms.card.exception.ExceptionConstants.USER_NOT_FOUND_MESSAGE;
import static com.example.ms.card.mapper.CardMapper.CARD_MAPPER;
import static com.example.ms.card.model.enums.CardStatus.ACTIVE;
import static com.example.ms.card.model.enums.CardStatus.BLOCKED;
import static com.example.ms.card.model.enums.CardStatus.DELETED;
import static com.example.ms.card.model.enums.CardStatus.STOLEN;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;
    UserClient userClient;
    CacheService cacheService;

    @LogDto
    @Override
    public CardResponseDto createCard(Long userId, CardRequestDto requestDto){
        if(!userClient.userExists(userId)){
            throw new NotFoundException(USER_NOT_FOUND_CODE, String.format(USER_NOT_FOUND_MESSAGE, userId));
        }

        var newCardEntity = CARD_MAPPER.buildCardEntity(userId, requestDto);
        newCardEntity = cardRepository.save(newCardEntity);
        cacheService.saveCardToCache(newCardEntity);
        cacheService.saveUserCardToCache(userId, newCardEntity);
        return CARD_MAPPER.toCardResponseDto(newCardEntity);
    }

    @LogDto
    @Override
    public CardResponseDto getCardById(Long id){
        CardEntity cardEntity;
        if(cacheService.getFromCache(MS_CARD + id) != null){
            cardEntity = cacheService.getFromCache(MS_CARD + id);
        } else {
            cardEntity = fetchActiveCardIfExists(id);
        }
        return CARD_MAPPER.toCardResponseDto(cardEntity);
    }

    @LogDto
    @LogExecutionTime
    @Override
    public List<GetCardsResponseDto> getCardsByUserId(Long userId){
        if(!userClient.userExists(userId)){
            throw new NotFoundException(USER_NOT_FOUND_CODE, String.format(USER_NOT_FOUND_MESSAGE, userId));
        }

        if(cacheService.getFromCache(MS_CARD_USER + userId) != null){
            return cacheService.getFromCache(MS_CARD_USER + userId);
        }

        var listCards = cardRepository.findAllByUserIdAndStatus(userId, ACTIVE);
        return listCards.stream()
                .map(CARD_MAPPER::toGetCardsResponseDto)
                .collect(Collectors.toList());
    }

    @LogDto
    @Override
    public void deleteCardById(Long id){
        var cardEntity = fetchActiveCardIfExists(id);
        cardEntity.setStatus(DELETED);
        cardEntity.setUpdateDate(LocalDateTime.now());
        cacheService.deleteCache(MS_CARD + id);
        cardRepository.save(cardEntity);
    }

    @LogDto
    @Override
    public void updateStolenCard(@PathVariable Long id){
        var cardEntity = fetchCardIfExists(id);
        if(cardEntity.getStatus().equals(ACTIVE)) {
            updateCard(id, cardEntity, STOLEN);
        }
        else if(cardEntity.getStatus().equals(STOLEN)) {
            updateCard(id, cardEntity, ACTIVE);
        }
        else throw new NotFoundException(CARD_NOT_FOUND_CODE, String.format(CARD_NOT_FOUND_MESSAGE, id));
    }

    @LogDto
    @Override
    public void updateBlockedCard(@PathVariable Long id){
        var cardEntity = fetchCardIfExists(id);
        if(cardEntity.getStatus().equals(ACTIVE)) {
            updateCard(id, cardEntity, BLOCKED);
        }
        else if(cardEntity.getStatus().equals(BLOCKED)) {
            updateCard(id, cardEntity, ACTIVE);
        }
        else throw new NotFoundException(CARD_NOT_FOUND_CODE, String.format(CARD_NOT_FOUND_MESSAGE, id));
    }

    private void updateCard(Long id, CardEntity cardEntity, CardStatus cardStatus){
        cardEntity.setStatus(cardStatus);
        cacheService.deleteCache(MS_CARD + id);
        cardRepository.save(cardEntity);
    }

    private CardEntity fetchActiveCardIfExists(Long id){
        return cardRepository.findByIdAndStatus(id, ACTIVE)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND_CODE, String.format(CARD_NOT_FOUND_MESSAGE, id)));
    }

    private CardEntity fetchCardIfExists(Long id){
        return cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND_CODE, String.format(CARD_NOT_FOUND_MESSAGE, id)));
    }
}
