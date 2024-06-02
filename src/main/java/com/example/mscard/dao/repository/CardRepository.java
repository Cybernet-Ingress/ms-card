package com.example.mscard.dao.repository;

import com.example.mscard.dao.entity.CardEntity;
import com.example.mscard.model.enums.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByUserIdAndStatus(Long userId, CardStatus status);
}
