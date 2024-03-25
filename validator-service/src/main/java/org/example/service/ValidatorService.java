package org.example.service;

import org.example.model.CsvPersonOnValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ValidatorService {

    public String testProcessFile() {
        return "Respuesta desde método testServiceValidator()";
    }

    public boolean testResponseJsonValidator(@RequestBody CsvPersonOnValidator csvPerson){
        return csvPerson.getJobTitle().equals("Editor");
    }

    public boolean validatorCsvObject(@RequestBody CsvPersonOnValidator csvPerson){
        return csvPerson.getSex().equals("Female") &&
                ( csvPerson.getJobTitle().equals("Haematologist") ||
                        csvPerson.getJobTitle().equals("Phytotherapist") );
    }

}
