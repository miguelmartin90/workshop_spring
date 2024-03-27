package org.example.controller;

import org.example.client.IServiceComValidator;
import org.example.model.CsvPerson;
import org.example.model.FileMetadata;
import org.example.dto.FileMetadataDTO;
import org.example.service.CsvFileProcessService;
import com.opencsv.exceptions.CsvException;
import org.example.service.ExcelFileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/process")
public class FileProcessController {

    private final CsvFileProcessService csvFileProcessService;
    private final ExcelFileProcessService excelFileProcessService;
    private final IServiceComValidator serviceComValidator;

    @Autowired
    public FileProcessController(CsvFileProcessService csvFileProcessService, ExcelFileProcessService excelFileProcessService, IServiceComValidator serviceComValidator){
        this.csvFileProcessService = csvFileProcessService;
        this.excelFileProcessService = excelFileProcessService;
        this.serviceComValidator = serviceComValidator;
    }

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
    }/*

    @PostMapping("/csv")
    public FileMetadata csvFileReader(@RequestBody FileMetadata file) throws IOException, CsvException {
        return csvFileProcessService.sendCsvObject(
                csvFileProcessService.csvFileReader(file.getPath())
        );
    }

   @PostMapping("/excel")
    public String excelFileReader(@RequestBody FileMetadata file) throws IOException {
        this.excelFileProcessService.excelFileReader(file.getPath());
        return file.getPath();
    }*/

    @PostMapping("/file")
    private FileMetadataDTO fileReader(@RequestBody FileMetadata file){
        String path = file.getPath();
        String typeOfFile = file.getTypeOfFile();

        try{
            if ( "csv".equalsIgnoreCase(typeOfFile) ) {
                file = csvFileProcessService.sendCsvObject(
                            csvFileProcessService.csvFileReader(path));

            } else if ("xlsx".equalsIgnoreCase(typeOfFile) || "xls".equalsIgnoreCase(typeOfFile)) {
                file = excelFileProcessService.sendExcelObject(
                        excelFileProcessService.excelFileReader(path)
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new FileMetadataDTO(file.getValidLines(), file.getInvalidLines());
    }

}