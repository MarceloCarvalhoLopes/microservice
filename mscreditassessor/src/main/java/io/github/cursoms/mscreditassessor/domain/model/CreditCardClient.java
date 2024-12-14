package io.github.cursoms.mscreditassessor.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardClient {
    private String name;
    private String brandCard;
    private BigDecimal limitClient;

}
