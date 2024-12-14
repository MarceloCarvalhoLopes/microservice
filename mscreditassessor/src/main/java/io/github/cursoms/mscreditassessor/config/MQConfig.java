package io.github.cursoms.mscreditassessor.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.issue-creditcard}")
    private String creditCardIssuanceLine;

    @Bean
    public Queue queueIssueCreditCard(){
        return new Queue(creditCardIssuanceLine,true);
    }

}
