package com.example.mscard.dao.entity;

import com.example.mscard.model.enums.CardBrand;
import com.example.mscard.model.enums.CardStatus;
import com.example.mscard.model.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
@ToString
@Builder
public class CardEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String pan;

    private String cardHolder;

    private BigDecimal balance;

    @Enumerated(STRING)
    private CardType type;

    @Enumerated(STRING)
    private CardBrand brand;

    @Enumerated(STRING)
    private CardStatus status;

    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
