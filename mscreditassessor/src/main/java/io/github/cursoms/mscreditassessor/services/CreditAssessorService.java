package io.github.cursoms.mscreditassessor.services;

import io.github.cursoms.mscreditassessor.domain.CreditCardClient;
import io.github.cursoms.mscreditassessor.domain.DataClient;
import io.github.cursoms.mscreditassessor.domain.StatusClient;
import io.github.cursoms.mscreditassessor.infra.clients.ClientResourceClient;
import io.github.cursoms.mscreditassessor.infra.clients.CreditCardResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final ClientResourceClient clientClient;
    private final CreditCardResourceClient creditCardClient;

    public StatusClient getStatusClient(String cpf) {
        //getDataClient - msclient
        //getCreditCardClient - mscreditcard

        ResponseEntity<DataClient> dataClientResponseEntity = clientClient.findByCpf(cpf);
        ResponseEntity<List<CreditCardClient>> creditCardCResponseEntity  = creditCardClient.findByCpf(cpf);

        return StatusClient
                .builder()
                .client(dataClientResponseEntity.getBody())
                .creditCard(creditCardCResponseEntity.getBody())
                .build();

    }

}
