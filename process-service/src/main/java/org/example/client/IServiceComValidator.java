package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name= "validator" , url = "localhost:8090")
public interface IServiceComValidator {

    @GetMapping("/validator/test")
    String testServiceValidator();

    /*@PostMapping("/csv/")
    String testServiceValidator();*/
}
