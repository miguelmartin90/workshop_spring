package org.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public String testProcessFile() {
        return "Respuesta desde método testServiceValidator()";
    }
}
