package com.example.ms.card.cache;

import com.example.ms.card.dao.entity.CardEntity;

public interface CacheService {
    void saveCardToCache(CardEntity cardEntity);

    void saveUserCardToCache(Long userId, CardEntity cardEntity);

    <T> T getFromCache(String key);

    void deleteCache(String key);
}
