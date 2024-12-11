package io.github.cursoms.mscreditassessor.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class CreditCard {

    private Long id;
    private String name;
    private String cardBrand;
    private BigDecimal limitCard;


}
