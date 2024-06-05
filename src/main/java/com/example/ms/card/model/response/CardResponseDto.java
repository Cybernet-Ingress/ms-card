package com.example.ms.card.model.response;

import com.example.ms.card.model.enums.CardBrand;
import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private Long id;
    private String pan;
    private String cardHolder;
    private BigDecimal balance;
    private CardType type;
    private CardBrand brand;
    private LocalDateTime insertDate;
    private CardStatus status;
    private LocalDateTime updateDate;
    private Long userId;
}
