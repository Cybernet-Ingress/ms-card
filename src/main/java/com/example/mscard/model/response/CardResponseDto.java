package com.example.mscard.model.response;

import com.example.mscard.model.enums.CardBrand;
import com.example.mscard.model.enums.CardStatus;
import com.example.mscard.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private CardStatus status;
    private Long userId;
}
