package com.example.ms.card.dao.repository;

import com.example.ms.card.dao.entity.CardEntity;
import com.example.ms.card.model.enums.CardStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
    Set<CardEntity> findAllByUserIdAndStatus(Long userId, CardStatus status);

    Optional<CardEntity> findByIdAndStatus(Long id, CardStatus cardStatus);

    Set<CardEntity> findByUserId(Long userId);
}
