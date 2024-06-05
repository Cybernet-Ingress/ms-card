package com.example.ms.card.dao.repository;

import com.example.ms.card.dao.entity.CardEntity;
import com.example.ms.card.model.enums.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByUserIdAndStatus(Long userId, CardStatus status);
}
