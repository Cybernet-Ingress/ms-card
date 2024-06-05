package com.example.ms.card.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheUtil {
    private final RedissonClient redissonClient;

    public <T> T getBucket(String key){
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket == null ? null : bucket.get();
    }

    public <T> void saveToCache(String key, T value, Long expireTime, TemporalUnit temporalUnit){
        var bucket = redissonClient.getBucket(key);
        bucket.set(value);
        if(expireTime == null) expireTime = 1L;
        if(temporalUnit == null) temporalUnit = ChronoUnit.DAYS;
        bucket.expire(Duration.of(expireTime, temporalUnit));
    }

    public boolean deleteCache(String key){
        var bucket = redissonClient.getBucket(key);
        if(bucket != null) return bucket.delete();
        return false;
    }
}
