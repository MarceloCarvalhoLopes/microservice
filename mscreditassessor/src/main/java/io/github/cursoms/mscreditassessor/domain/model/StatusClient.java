package io.github.cursoms.mscreditassessor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusClient {

    private DataClient client;
    private List<CreditCardClient> creditCard;

}
