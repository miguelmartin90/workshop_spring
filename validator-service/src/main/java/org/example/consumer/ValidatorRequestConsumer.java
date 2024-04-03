package org.example.consumer;

import org.example.model.CsvPersonOnValidator;
import org.example.model.ExcelSafetyDataOnValidator;
import org.example.producer.ValidatorResponsePublisher;
import org.example.service.CsvValidatorService;
import org.example.service.ExcelValidatorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ValidatorRequestConsumer {

    private ValidatorResponsePublisher validatorResponsePublisher;
    private CsvValidatorService csvValidatorService;
    private ExcelValidatorService excelValidatorService;

    @Autowired
    public ValidatorRequestConsumer(ValidatorResponsePublisher validatorResponsePublisher, CsvValidatorService csvValidatorService, ExcelValidatorService excelValidatorService) {
        this.validatorResponsePublisher = validatorResponsePublisher;
        this.csvValidatorService = csvValidatorService;
        this.excelValidatorService = excelValidatorService;
    }

    @RabbitListener(queues = {"reader-request-queue"})
    public void receiverMessage(CsvPersonOnValidator csvPerson) throws ParseException {

        validatorResponsePublisher.produceOrder(
                csvValidatorService.validatorCsvObject(csvPerson)
        );
    }

    @RabbitListener(queues = {"reader-request-queue"})
    public void receiverMessage(ExcelSafetyDataOnValidator excelSafetyData) throws ParseException {

        validatorResponsePublisher.produceOrder(
                excelValidatorService.validatorExcelObject(excelSafetyData)
        );
    }

}
