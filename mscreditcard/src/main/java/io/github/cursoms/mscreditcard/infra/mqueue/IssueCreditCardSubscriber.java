package io.github.cursoms.mscreditcard.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class IssueCreditCardSubscriber {

    @RabbitListener(queues = "${mq.queues.issue-creditcard}")
    public void receiveIssuanceRequest(@Payload String payload){
        System.out.println(payload);
    }


}
