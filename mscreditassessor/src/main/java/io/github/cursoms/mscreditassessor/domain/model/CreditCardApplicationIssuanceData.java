package io.github.cursoms.mscreditassessor.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardApplicationIssuanceData {

    private Long id;
    private String cpf;
    private String address;
    private BigDecimal limitCard;

}
