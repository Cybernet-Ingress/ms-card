package com.example.ms.card.model.response;

import com.example.ms.card.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCardsResponseDto implements Serializable {
    private static final long serialVersionUID  = 8049606724L;

    private Long id;
    private String pan;
    private BigDecimal balance;
    private CardType type;
}
