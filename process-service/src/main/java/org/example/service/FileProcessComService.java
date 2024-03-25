package org.example.service;

import org.example.client.IServiceComValidator;
import org.example.model.CsvPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FileProcessComService {

    private IServiceComValidator serviceComValidator;

    @Autowired
    public FileProcessComService(IServiceComValidator serviceComValidator) {
        this.serviceComValidator = serviceComValidator;
    }

    public String testValidator(){
        return serviceComValidator.testServiceValidator();
    }


}
