package com.example.ms.card.model.request;

import com.example.ms.card.model.enums.CardBrand;
import com.example.ms.card.model.enums.CardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    @Pattern(regexp="\\d{16}", message = "card pan number must contains 16 digits only")
    private String pan;

    @NotBlank(message = "card holder may not be null")
    private String cardHolder;

    @NotBlank(message = "card type may not be null")
    private CardType type;

    @NotBlank(message = "card brand may not be null")
    private CardBrand brand;
}
