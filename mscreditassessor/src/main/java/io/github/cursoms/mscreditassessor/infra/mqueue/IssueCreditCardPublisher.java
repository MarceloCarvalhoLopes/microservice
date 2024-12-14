package io.github.cursoms.mscreditassessor.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cursoms.mscreditassessor.domain.model.CreditCardApplicationIssuanceData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueCreditCardPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssueCreditCard;


    public void IssueCreditCard(CreditCardApplicationIssuanceData data) throws JsonProcessingException {
        var json = convertIntoJSon(data);
        rabbitTemplate.convertAndSend(queueIssueCreditCard.getName(),json);
    }

    private String convertIntoJSon(CreditCardApplicationIssuanceData data) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
