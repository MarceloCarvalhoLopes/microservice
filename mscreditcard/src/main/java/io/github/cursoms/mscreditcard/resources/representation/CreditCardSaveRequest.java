package io.github.cursoms.mscreditcard.resources.representation;

import io.github.cursoms.mscreditcard.domain.CardBrand;
import io.github.cursoms.mscreditcard.domain.CreditCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardSaveRequest {

    private String name;
    private CardBrand cardBrand;
    private BigDecimal income;
    private BigDecimal limitCard;

    public CreditCard toModel(){
        return new CreditCard(name, cardBrand, income, limitCard);
    }
}
