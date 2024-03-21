package org.example;

import com.opencsv.exceptions.CsvException;
import org.example.service.CsvFileProcessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
public class FileProcessApplication {

    public static void main(String[] args) throws IOException, CsvException {
        SpringApplication.run(FileProcessApplication.class, args);
    }
}
