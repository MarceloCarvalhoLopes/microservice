package io.github.cursoms.mscreditcard.resources.representation;

import io.github.cursoms.mscreditcard.domain.CardBrand;
import io.github.cursoms.mscreditcard.domain.ClientCreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardByClientResponse {
    private String name;
    private String cardBrand;
    private BigDecimal limitReleased;


    public static CreditCardByClientResponse fromModel(ClientCreditCard model) {
        return new CreditCardByClientResponse(
            model.getCreditCard().getName(),
            model.getCreditCard().getCardBrand().toString(),
            model.getLimitClient()
        );
    }
}
