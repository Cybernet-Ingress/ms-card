package com.example.mscard.model.request;

import com.example.mscard.model.enums.CardBrand;
import com.example.mscard.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {
    private String pan;
    private String cardHolder;
    private CardType type;
    private CardBrand brand;
}
