package com.example.ms.card.dao.entity;

import com.example.ms.card.model.enums.CardBrand;
import com.example.ms.card.model.enums.CardStatus;
import com.example.ms.card.model.enums.CardType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class CardEntity implements Serializable {
    private static final long serialVersionUID  = 8049606725L;

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

    private LocalDateTime insertDate;

    @Enumerated(STRING)
    private CardStatus status;

    private LocalDateTime updateDate;

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
