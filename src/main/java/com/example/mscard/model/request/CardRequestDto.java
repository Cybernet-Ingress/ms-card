package com.example.mscard.model.request;

import com.example.mscard.model.enums.CardBrand;
import com.example.mscard.model.enums.CardType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    //@Size(min = 16, max = 16, message = "card pan number must contains 16 digits only")
    //@Pattern(regexp="\\d{16}", message = "card pan number must contains 16 digits only")
    private String pan;

    //@NotBlank(message = "card holder may not be null")
    private String cardHolder;
    private CardType type;
    private CardBrand brand;
}
