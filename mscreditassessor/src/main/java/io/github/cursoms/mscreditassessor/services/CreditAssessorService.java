package io.github.cursoms.mscreditassessor.services;

import feign.FeignException;
import io.github.cursoms.mscreditassessor.domain.CreditCardClient;
import io.github.cursoms.mscreditassessor.domain.DataClient;
import io.github.cursoms.mscreditassessor.domain.StatusClient;
import io.github.cursoms.mscreditassessor.infra.clients.ClientResourceClient;
import io.github.cursoms.mscreditassessor.infra.clients.CreditCardResourceClient;
import io.github.cursoms.mscreditassessor.services.excptions.DataClientNotFoundException;
import io.github.cursoms.mscreditassessor.services.excptions.ErrorComunicationMicroserviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final ClientResourceClient clientClient;
    private final CreditCardResourceClient creditCardClient;

    public StatusClient getStatusClient(String cpf) throws DataClientNotFoundException,
            ErrorComunicationMicroserviceException {
        //getDataClient - msclient
        //getCreditCardClient - mscreditcard

        try {
            ResponseEntity<DataClient> dataClientResponseEntity = clientClient.findByCpf(cpf);
            ResponseEntity<List<CreditCardClient>> creditCardCResponseEntity  = creditCardClient.findByCpf(cpf);

            return StatusClient
                    .builder()
                    .client(dataClientResponseEntity.getBody())
                    .creditCard(creditCardCResponseEntity.getBody())
                    .build();

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw  new ErrorComunicationMicroserviceException(e.getMessage(),status);
        }

    }

}
