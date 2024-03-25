package org.example.controller;

import org.example.model.CsvPersonOnValidator;
import org.example.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/validator")
public class ValidatorController {

    private final ValidatorService validatorService;

    @Autowired
    public ValidatorController(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @GetMapping("/test")
    public String testServiceValidator(){
        return "Response Validator: desde testServiceValidator()";
    }

    @GetMapping("/test-process")
    public String testProcessFile(){
        return validatorService.testProcessFile();
    }

    @PostMapping("/json")
    boolean testResponseJsonValidator(@RequestBody CsvPersonOnValidator csvPerson){
        return validatorService.testResponseJsonValidator(csvPerson);
    }

    @PostMapping("/csv-validator/")
    boolean validatorCsvObject(@RequestBody CsvPersonOnValidator csvPerson) throws ParseException {
        return validatorService.validatorCsvObject(csvPerson);
    }
}
