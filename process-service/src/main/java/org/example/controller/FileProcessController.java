package org.example.controller;

import org.example.response.FileProcess;
import org.example.service.FileProcessService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileProcessController {

    private FileProcessService fileReaderService;

    @Autowired
    public FileProcessController(FileProcessService fileReaderService){
        this.fileReaderService = fileReaderService;
    }

    @GetMapping("/test")
    public String pruebaControllerReader(){
        return "Retorno desde pruebaControllerReader";
    }

    @PostMapping("/csv/")
    public FileProcess csvFileReader(@RequestBody FileProcess fileReader) throws IOException, CsvException {
        this.fileReaderService.csvFileReader(fileReader);
        return fileReader;
    }

/*    @PostMapping("/csv")
    public String csvReader(@RequestParam("file") MultipartFile file) throws IOException, CsvException {

        Reader reader = new InputStreamReader(file.getInputStream());

        // parse CSV
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
        List<String[]> rows = csvReader.readAll();
        System.out.println("hello---------" + rows);

        return "Processed " + rows.size() + " rows!";
    }*/

   @PostMapping("/excel")
    public String parseExcel(String cadena) {
        // parse Excel
        return cadena + "... Hello";
    }
}