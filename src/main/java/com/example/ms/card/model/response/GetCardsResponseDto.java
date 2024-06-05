package com.example.ms.card.model.response;

import com.example.ms.card.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCardsResponseDto {
    private Long id;
    private String pan;
    private BigDecimal balance;
    private CardType type;
}
