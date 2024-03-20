package org.example.controller;

import org.example.response.CsvPerson;
import org.example.response.FilePath;
import org.example.service.CsvFileProcessService;
import com.opencsv.exceptions.CsvException;
import org.example.service.ExcelFileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileProcessController {

    private CsvFileProcessService csvFileProcessService;
    private ExcelFileProcessService excelFileProcessService;

    @Autowired
    public FileProcessController(CsvFileProcessService csvFileProcessService, ExcelFileProcessService excelFileProcessService){
        this.csvFileProcessService = csvFileProcessService;
        this.excelFileProcessService = excelFileProcessService;
    }
/*
    @Autowired
    public FileProcessController(ExcelFileProcessService excelFileProcessService) {
        this.excelFileProcessService = excelFileProcessService;
    }*/

    @GetMapping("/test/")
    public String pruebaControllerReader(){
        return "Retorno desde pruebaControllerReader";
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