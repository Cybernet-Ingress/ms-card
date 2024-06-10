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
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
@ToString(of = {"id", "status", "userId"})
@Builder
@FieldDefaults(level = PRIVATE)
public class CardEntity implements Comparable<CardEntity>, Serializable {
    static final long serialVersionUID  = 8049606724L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String pan;

    String cardHolder;

    BigDecimal balance;

    @Enumerated(STRING)
    CardType type;

    @Enumerated(STRING)
    CardBrand brand;

    @CreationTimestamp
    LocalDateTime createdAt;

    @Enumerated(STRING)
    CardStatus status;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    Long userId;

    // Cannot invoke "java.time.LocalDateTime.compareTo(java.time.chrono.ChronoLocalDateTime)" because "this.createdAt" is null
    @Override
    public int compareTo(CardEntity other) {
        return this.createdAt.compareTo(other.getCreatedAt());
    }

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
