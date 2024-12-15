package io.github.cursoms.mscreditcard.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cursoms.mscreditcard.domain.model.ClientCreditCard;
import io.github.cursoms.mscreditcard.domain.model.CreditCard;
import io.github.cursoms.mscreditcard.domain.model.CreditCardApplicationIssuanceData;
import io.github.cursoms.mscreditcard.repositories.ClientCreditCardRepository;
import io.github.cursoms.mscreditcard.repositories.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueCreditCardSubscriber {

    private final CreditCardRepository creditCardRepository;
    private final ClientCreditCardRepository clientCreditCardRepository;

    @RabbitListener(queues = "${mq.queues.issue-creditcard}")
    public void receiveIssuanceRequest(@Payload String payload){


        try {
            var mapper = new ObjectMapper();

            CreditCardApplicationIssuanceData data = mapper.readValue(payload, CreditCardApplicationIssuanceData.class);
            CreditCard creditCard = creditCardRepository.findById(data.getId()).orElseThrow();

            ClientCreditCard clientCreditCard = new ClientCreditCard();
            clientCreditCard.setCreditCard(creditCard);
            clientCreditCard.setCpf(data.getCpf());
            clientCreditCard.setLimitCard(data.getLimitCard());

            clientCreditCardRepository.save(clientCreditCard);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

