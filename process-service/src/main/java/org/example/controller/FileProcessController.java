package org.example.controller;

import org.example.client.IServiceComValidator;
import org.example.model.CsvPerson;
import org.example.model.FileMetadata;
import org.example.service.CsvFileProcessService;
import com.opencsv.exceptions.CsvException;
import org.example.service.ExcelFileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/process")
public class FileProcessController {

    private CsvFileProcessService csvFileProcessService;
    private ExcelFileProcessService excelFileProcessService;
    private IServiceComValidator serviceComValidator;

    @Autowired
    public FileProcessController(CsvFileProcessService csvFileProcessService, ExcelFileProcessService excelFileProcessService, IServiceComValidator serviceComValidator){
        this.csvFileProcessService = csvFileProcessService;
        this.excelFileProcessService = excelFileProcessService;
        this.serviceComValidator = serviceComValidator;
    }
/*
    @Autowired
    public FileProcessController(ExcelFileProcessService excelFileProcessService) {
        this.excelFileProcessService = excelFileProcessService;
    }*/

    @GetMapping("/test")
    public String testServiceFileProcess(){
        return csvFileProcessService.testServiceFileProcess();
    }

    @GetMapping("/test-validator")
    public String testServiceValidator(){
        return serviceComValidator.testServiceValidator();
    }

    @PostMapping("/json")
    public boolean testResponseJsonValidator(@RequestBody CsvPerson csvPerson){
        return serviceComValidator.testResponseJsonValidator(csvPerson);
    }

    @PostMapping("/csv/")
    public FileMetadata csvFileReader(@RequestBody FileMetadata path) throws IOException, CsvException {
        /*csvFileProcessService.csvFileReader(path.getPath());
        return path.getPath();*/
//        return serviceComValidator.csvLineValidator(csvFileProcessService.csvFileReader(path.getPath()));
        return csvFileProcessService.sendObject(
                csvFileProcessService.csvFileReader(path.getPath())
        );
    }

   @PostMapping("/excel/")
    public String excelFileReader(@RequestBody FileMetadata path) throws IOException {
        this.excelFileProcessService.excelFileReader(path.getPath());
        return path.getPath();
    }
}