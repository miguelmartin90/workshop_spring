package org.example.controller;

import org.example.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
