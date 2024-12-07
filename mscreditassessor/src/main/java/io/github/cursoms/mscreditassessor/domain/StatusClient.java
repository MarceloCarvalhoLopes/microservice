package io.github.cursoms.mscreditassessor.domain;

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

    private DataClient clients;
    private List<CreditCardClient> creditCardClient;

}
