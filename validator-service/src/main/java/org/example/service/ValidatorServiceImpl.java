package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public String testServiceValidator() {
        return "Respuesta desde método testServiceValidator()";
    }
}
