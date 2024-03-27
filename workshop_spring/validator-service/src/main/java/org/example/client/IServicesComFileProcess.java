package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "process-service", url = "localhost:8030")
public interface IServicesComFileProcess {

    @GetMapping("/process/test")
    String testServiceFileProcess();
}
