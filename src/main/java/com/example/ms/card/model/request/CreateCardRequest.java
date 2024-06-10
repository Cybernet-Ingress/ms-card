package com.example.ms.card.model.request;

import com.example.ms.card.model.enums.CardBrand;
import com.example.ms.card.model.enums.CardType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.example.ms.card.exception.FieldExceptionConstants.CARD_PAN_16_DIGITS_ONLY;
import static com.example.ms.card.exception.FieldExceptionConstants.INVALID_CARD_BRAND;
import static com.example.ms.card.exception.FieldExceptionConstants.INVALID_CARD_HOLDER;
import static com.example.ms.card.exception.FieldExceptionConstants.INVALID_CARD_TYPE;
import static lombok.AccessLevel.PRIVATE;

@Data
@ToString(of = {"cardHolder", "type", "brand"})
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CreateCardRequest {

    @NotNull(message = CARD_PAN_16_DIGITS_ONLY)
    @Pattern(regexp="\\d{16}", message = CARD_PAN_16_DIGITS_ONLY)
    String pan;

    @NotNull(message = INVALID_CARD_HOLDER)
    String cardHolder;

    @NotNull(message = INVALID_CARD_TYPE)
    CardType type;

    @NotNull(message = INVALID_CARD_BRAND)
    CardBrand brand;
}
