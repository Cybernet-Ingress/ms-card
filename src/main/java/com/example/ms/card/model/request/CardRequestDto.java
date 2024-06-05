package com.example.ms.card.model.request;

import com.example.ms.card.model.enums.CardBrand;
import com.example.ms.card.model.enums.CardType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.example.ms.card.exception.FieldExceptionConstants.CARD_PAN_16_DIGITS_ONLY;
import static com.example.ms.card.exception.FieldExceptionConstants.INVALID_CARD_BRAND;
import static com.example.ms.card.exception.FieldExceptionConstants.INVALID_CARD_HOLDER;
import static com.example.ms.card.exception.FieldExceptionConstants.INVALID_CARD_TYPE;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CardRequestDto {

    @Pattern(regexp="\\d{16}", message = CARD_PAN_16_DIGITS_ONLY)
    String pan;

    @NotNull(message = INVALID_CARD_HOLDER)
    String cardHolder;

    @NotNull(message = INVALID_CARD_TYPE)
    CardType type;

    @NotNull(message = INVALID_CARD_BRAND)
    CardBrand brand;
}
