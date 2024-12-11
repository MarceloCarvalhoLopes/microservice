package io.github.cursoms.mscreditassessor.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardApproved {
    private String creditCard;
    private String brand;
    private BigDecimal limitApproved;

}
