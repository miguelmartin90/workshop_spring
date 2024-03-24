package org.example.service;

import org.example.client.IServiceComValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
