package com.example.ms.card.model.response;

import com.example.ms.card.model.enums.CardBrand;
import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.enums.CardType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString(of = {"id", "status", "userId"})
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CardResponse {
    Long id;
    String pan;
    String cardHolder;
    BigDecimal balance;
    CardType type;
    CardBrand brand;
    LocalDateTime createdAt;
    CardStatus status;
    LocalDateTime updatedAt;
    Long userId;
}
