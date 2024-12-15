package io.github.cursoms.mscreditassessor.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardApproved {
    private String creditCard;
    private String cardBrand;
    private BigDecimal limitCard;

}
