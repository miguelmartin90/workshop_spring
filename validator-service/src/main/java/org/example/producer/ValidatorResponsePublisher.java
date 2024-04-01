package org.example.producer;

import org.example.rabbitCommunication.MessagingServiceValidatorConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorResponsePublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ValidatorResponsePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produceOrder(boolean validLineStatus){
        System.out.println("\n\n--> Mensaje devuelto a PROCESS-SERVICE...");
        rabbitTemplate.convertAndSend(
                MessagingServiceValidatorConfig.EXCHANGE,
                MessagingServiceValidatorConfig.ROUTING_KEY,
                validLineStatus);
    }
}
