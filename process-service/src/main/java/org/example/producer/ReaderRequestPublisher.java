package org.example.producer;

import org.example.model.CsvPerson;
import org.example.model.ExcelSafetyData;
import org.example.rabbitCommunication.MessagingServiceConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderRequestPublisher {

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

    public String produceOrder(ExcelSafetyData excelSafetyData){
        rabbitTemplate.convertAndSend(
                MessagingServiceConfig.EXCHANGE,
                MessagingServiceConfig.ROUTING_KEY,
                excelSafetyData);
        return "\n--> Orden enviada a VALIDATOR-SERVICE...";
    }

   /* public <T> String produceOrder(T data){
        rabbitTemplate.convertAndSend(
                MessagingServiceConfig.EXCHANGE,
                MessagingServiceConfig.ROUTING_KEY,
                data);
        return "\n--> Orden enviada a VALIDATOR-SERVICE...";
    }*/
}
