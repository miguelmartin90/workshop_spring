package org.example.consumer;

import org.example.model.CsvPersonOnValidator;
import org.example.producer.ValidatorResponsePublisher;
import org.example.service.CsvValidatorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ValidatorRequestConsumer {


    private ValidatorResponsePublisher validatorResponsePublisher;
    private CsvValidatorService csvValidatorService;

    @Autowired
    public ValidatorRequestConsumer(ValidatorResponsePublisher validatorResponsePublisher, CsvValidatorService csvValidatorService) {
        this.validatorResponsePublisher = validatorResponsePublisher;
        this.csvValidatorService = csvValidatorService;
    }

    @RabbitListener(queues = {"reader-request-queue"})
    public void receiverMessage(CsvPersonOnValidator csvPerson) throws ParseException {

        // Procesamiento de mensaje
        //orderStatus.setStatus("\n\n--> 'Recibido en Backend-worked...");
        //System.out.println(orderStatus.getStatus());

        validatorResponsePublisher.produceOrder(
                csvValidatorService.validatorCsvObject(csvPerson)
        );
    }
}
