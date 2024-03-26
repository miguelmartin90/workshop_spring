package org.example.controller;

import org.example.model.CsvPersonOnValidator;
import org.example.model.ExcelSafetyDataOnValidator;
import org.example.service.ExcelValidatorService;
import org.example.service.CsvValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/validator")
public class ValidatorController {

    private final CsvValidatorService csvValidatorService;
    private final ExcelValidatorService excelValidatorService;

    @Autowired
    public ValidatorController(CsvValidatorService csvValidatorService, ExcelValidatorService excelValidatorService) {
        this.csvValidatorService = csvValidatorService;
        this.excelValidatorService = excelValidatorService;
    }

    @GetMapping("/test")
    public String testServiceValidator(){
        return "Response Validator: desde testServiceValidator()";
    }

    @GetMapping("/test-process")
    public String testProcessFile(){
        return csvValidatorService.testProcessFile();
    }

    @PostMapping("/json")
    boolean testResponseJsonValidator(@RequestBody CsvPersonOnValidator csvPerson){
        return csvValidatorService.testResponseJsonValidator(csvPerson);
    }

    @PostMapping("/csv")
    boolean validatorCsvObject(@RequestBody CsvPersonOnValidator csvPerson) throws ParseException {
        return csvValidatorService.validatorCsvObject(csvPerson);
    }

    @PostMapping("/excel")
    boolean validatorXlsxObject(@RequestBody ExcelSafetyDataOnValidator excelSafetyData){
        return excelValidatorService.validatorExcelObject(excelSafetyData);
    }
}
