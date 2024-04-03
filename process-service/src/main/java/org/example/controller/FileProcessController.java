package org.example.controller;

import org.example.consumer.ReaderResponseConsumer;
import org.example.feignCommunication.IServiceComValidator;
import org.example.model.CsvPerson;
import org.example.model.FileMetadata;
import org.example.dto.FileMetadataDTO;
import org.example.service.CsvFileProcessService;
import org.example.service.ExcelFileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/process")
public class FileProcessController {

    private final CsvFileProcessService csvFileProcessService;
    private final ExcelFileProcessService excelFileProcessService;
    private final IServiceComValidator serviceComValidator;
    private final ReaderResponseConsumer readerResponseConsumer;

    private FileMetadataDTO fileMetadataDto;
    private FileMetadata fileMetadata;

    @Autowired
    public FileProcessController(CsvFileProcessService csvFileProcessService, ExcelFileProcessService excelFileProcessService, IServiceComValidator serviceComValidator, ReaderResponseConsumer readerResponseConsumer, FileMetadata fileMetadata){
        this.csvFileProcessService = csvFileProcessService;
        this.excelFileProcessService = excelFileProcessService;
        this.serviceComValidator = serviceComValidator;
        this.readerResponseConsumer = readerResponseConsumer;
        this.fileMetadata = fileMetadata;
    }

   /* @GetMapping("/test")
    public String testServiceFileProcess(){
        return csvFileProcessService.testServiceFileProcess();
    }*/

    @GetMapping("/test-validator")
    public String testServiceValidator(){
        return serviceComValidator.testServiceValidator();
    }

    @PostMapping("/json")
    public boolean testResponseJsonValidator(@RequestBody CsvPerson csvPerson){
        return serviceComValidator.testResponseJsonValidator(csvPerson);
    }

    @PostMapping("/file-feign")
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

    @PostMapping("/file-rabbit")
    private FileMetadataDTO fileReaderRabbit(@RequestBody FileMetadata file) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = new CompletableFuture<>();

        String path = file.getPath();
        String typeOfFile = file.getTypeOfFile();
        try {
            if ("csv".equalsIgnoreCase(typeOfFile)) {
                csvFileProcessService.sendCsvObjectWithRabbit(
                csvFileProcessService.csvFileReader(path));

            } else if ("xlsx".equalsIgnoreCase(typeOfFile) || "xls".equalsIgnoreCase(typeOfFile)) {
                excelFileProcessService.sendExcelObjectWithRabbit(
                excelFileProcessService.excelFileReader(path)
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(8*1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        return new FileMetadataDTO(
                fileMetadata.getValidLines(),
                fileMetadata.getInvalidLines()
        );
    }
}