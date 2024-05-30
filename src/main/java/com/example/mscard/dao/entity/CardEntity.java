package com.example.mscard.dao.entity;

import com.example.mscard.model.enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
@Builder
public class CardEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String pan;
    private String cardHolder;

    @Enumerated(STRING)
    private CardStatus status;

    private Long usedId;
}
