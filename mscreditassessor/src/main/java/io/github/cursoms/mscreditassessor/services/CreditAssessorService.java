package io.github.cursoms.mscreditassessor.services;

import feign.FeignException;
import io.github.cursoms.mscreditassessor.domain.model.*;
import io.github.cursoms.mscreditassessor.infra.clients.ClientResourceClient;
import io.github.cursoms.mscreditassessor.infra.clients.CreditCardResourceClient;
import io.github.cursoms.mscreditassessor.infra.mqueue.IssueCreditCardPublisher;
import io.github.cursoms.mscreditassessor.services.excptions.DataClientNotFoundException;
import io.github.cursoms.mscreditassessor.services.excptions.ErroIssueCreditCardException;
import io.github.cursoms.mscreditassessor.services.excptions.ErrorComunicationMicroserviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final ClientResourceClient clientClient;
    private final CreditCardResourceClient creditCardClient;
    private final IssueCreditCardPublisher issueCreditCardPublisher;

    public StatusClient getStatusClient(String cpf) throws DataClientNotFoundException,
            ErrorComunicationMicroserviceException {
        //getDataClient - msclient
        //getCreditCardClient - mscreditcard

        try {
            ResponseEntity<DataClient> dataClientResponseEntity = clientClient.findByCpf(cpf);
            ResponseEntity<List<CreditCardClient>> creditCardCResponseEntity = creditCardClient.findByCpf(cpf);

            return StatusClient
                    .builder()
                    .client(dataClientResponseEntity.getBody())
                    .creditCard(creditCardCResponseEntity.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw new ErrorComunicationMicroserviceException(e.getMessage(), status);
        }

    }

    public ReturnAssessmentClient creditCardAssessmentClient(String cpf, Long income)
            throws DataClientNotFoundException, ErrorComunicationMicroserviceException {

        try {
            ResponseEntity<DataClient> dataClientResponseEntity = clientClient.findByCpf(cpf);
            ResponseEntity<List<CreditCard>> creditCardResponseEntity = creditCardClient.getCreditCardIncomeUntil(income);

            List<CreditCard> creditCardList = creditCardResponseEntity.getBody();
            var ListCreditCardApproved = creditCardList.stream().map(creditCard -> {

                DataClient dataClient = dataClientResponseEntity.getBody();

                BigDecimal limitBasic = creditCard.getLimitCard();
                BigDecimal ageBD = BigDecimal.valueOf(dataClient.getAge());

                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApprovodClient = factor.multiply(limitBasic);

                CreditCardApproved approved = new CreditCardApproved();
                approved.setCreditCard(creditCard.getName());
                approved.setCardBrand(creditCard.getCardBrand());
                approved.setLimitCard(limitApprovodClient);

                return  approved;
            }).collect(Collectors.toList());

            return new ReturnAssessmentClient(ListCreditCardApproved);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw new ErrorComunicationMicroserviceException(e.getMessage(), status);
        }


    }

    public ProtocolIssueCreditCard  IssueCreditCard(CreditCardApplicationIssuanceData data){
        try {
            issueCreditCardPublisher.IssueCreditCard(data);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocolIssueCreditCard(protocolo);
        }catch (Exception e){
            throw new ErroIssueCreditCardException(e.getMessage());
        }
    }

}
