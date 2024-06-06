package com.example.ms.card.cache;

import com.example.ms.card.dao.entity.CardEntity;
import com.example.ms.card.model.response.GetCardsResponseDto;
import com.example.ms.card.util.CacheUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.example.ms.card.cache.CacheKeyPrefixConstants.MS_CARD;
import static com.example.ms.card.cache.CacheKeyPrefixConstants.MS_CARD_USER;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CacheServiceImpl implements CacheService {
    CacheUtil cacheUtil;
    @Async
    @Override
    public void saveCardToCache(CardEntity cardEntity) {
        String cacheKey = MS_CARD + cardEntity.getId();
        cacheUtil.saveToCache(cacheKey, cardEntity, 7L, ChronoUnit.DAYS);
    }

    @Async
    @Override
    public void saveUserCardToCache(Long userId, CardEntity cardEntity) {
        String cacheKey = MS_CARD_USER + userId;
        var newCard = GetCardsResponseDto.builder()
                .id(cardEntity.getId())
                .pan(cardEntity.getPan())
                .balance(cardEntity.getBalance())
                .type(cardEntity.getType())
                .build();

        var listCards = getFromCache(cacheKey);
        if (listCards == null) {
            listCards = new ArrayList<GetCardsResponseDto>();
        }
        ((List<GetCardsResponseDto>) listCards).add(newCard);

        cacheUtil.saveToCache(cacheKey, listCards, 7L, ChronoUnit.DAYS);
    }

    @Override
    public <T> T getFromCache(String key) {
        return cacheUtil.getBucket(key);
    }

    @Async
    @Override
    public void deleteCache(String key) {
        cacheUtil.deleteCache(key);
    }
}
