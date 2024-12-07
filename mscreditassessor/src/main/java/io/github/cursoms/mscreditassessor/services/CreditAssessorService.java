package io.github.cursoms.mscreditassessor.services;

import io.github.cursoms.mscreditassessor.domain.DataClient;
import io.github.cursoms.mscreditassessor.domain.StatusClient;
import io.github.cursoms.mscreditassessor.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final ClientResourceClient clientsClient;

    public StatusClient getStatusClient(String cpf) {
        //getDataClient - msclient
        //getCreditCardClient - mscreditcard

        ResponseEntity<DataClient> responseEntity = clientsClient.findByCpf(cpf);

        return StatusClient
                .builder()
                .clients(responseEntity.getBody())
                .build();

    }

}
