package org.example.service;

import org.example.client.ServicesComFileProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ServiceComValidator implements ValidatorService {


    private ServicesComFileProcess servicesComFileProcess;

    @Autowired
    public ServiceComValidator(ServicesComFileProcess servicesComFileProcess) {
        this.servicesComFileProcess = servicesComFileProcess;
    }

    @Override
    public String testServiceValidator() {
        return servicesComFileProcess.testServiceFileProcess();
    }
}
