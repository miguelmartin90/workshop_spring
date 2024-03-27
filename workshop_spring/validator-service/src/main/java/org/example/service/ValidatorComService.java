package org.example.service;

import org.example.client.IServicesComFileProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ValidatorComService implements ValidatorService {

    private IServicesComFileProcess servicesComFileProcess;

    @Autowired
    public ValidatorComService(IServicesComFileProcess servicesComFileProcess) {
        this.servicesComFileProcess = servicesComFileProcess;
    }

    @Override
    public String testProcessFile() {
        return servicesComFileProcess.testServiceFileProcess();
    }
}
