package io.github.cursoms.mscreditcard.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;
    private BigDecimal income;
    private BigDecimal limitCard;

    public CreditCard(String name, CardBrand cardBrand, BigDecimal income, BigDecimal limitCard) {
        this.name = name;
        this.cardBrand = cardBrand;
        this.income = income;
        this.limitCard = limitCard;
    }
}
