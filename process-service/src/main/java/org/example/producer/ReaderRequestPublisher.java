package org.example.producer;

import org.example.model.CsvPerson;
import org.example.rabbitCommunication.MessagingServiceConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderRequestPublisher {

    // Enviar los mensajes a través de RabbitMQ
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String produceOrder(CsvPerson person){
        //OrderStatus orderStatus = new OrderStatus(order,"in process", "Su orden fue enviada a Backend-Worked");
        rabbitTemplate.convertAndSend(
                MessagingServiceConfig.EXCHANGE,
                MessagingServiceConfig.ROUTING_KEY,
                person);
        return "\n--> Orden enviada a VALIDATOR-SERVICE...";
    }
}
