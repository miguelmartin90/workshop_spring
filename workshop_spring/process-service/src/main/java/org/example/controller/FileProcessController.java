package org.example.controller;

import org.example.client.IServiceComValidator;
import org.example.model.FilePath;
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


    @PostMapping("/csv/")
    public String csvFileReader(@RequestBody FilePath path) throws IOException, CsvException {
        this.csvFileProcessService.csvFileReader(path.getPath());
        return path.getPath();
    }

   @PostMapping("/excel/")
    public String excelFileReader(@RequestBody FilePath path) throws IOException {
        this.excelFileProcessService.excelFileReader(path.getPath());
        return path.getPath();
    }
}