package org.example.client;

import org.example.model.CsvPerson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "validator" , url = "localhost:8090")
public interface IServiceComValidator {

    @GetMapping("/validator/test")
    String testServiceValidator();

    @PostMapping("/validator/json")
    boolean testResponseJsonValidator(@RequestBody CsvPerson csvPerson);

    @PostMapping("/validator/csv-validator/")
    boolean csvLineValidator(@RequestBody CsvPerson csvPerson);
}
