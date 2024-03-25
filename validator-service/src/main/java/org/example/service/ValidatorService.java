package org.example.service;

import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.example.model.CsvPersonOnValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorService {

    CsvPersonOnValidator csvPerson;

    /*@Autowired
    public ValidatorService(CsvPersonOnValidator csvPerson) {
        this.csvPerson = csvPerson;
    }*/
    public String testProcessFile() {
        return "Respuesta desde método testServiceValidator()";
    }

    public boolean testResponseJsonValidator(@RequestBody CsvPersonOnValidator csvPerson){
        return csvPerson.getJobTitle().equals("Editor");
    }

    /*public boolean validatorCsvObject(@RequestBody CsvPersonOnValidator csvPerson){
        return csvPerson.getSex().equals("Female") &&
                ( csvPerson.getJobTitle().equals("Haematologist") ||
                        csvPerson.getJobTitle().equals("Phytotherapist") );
    }*/

    public boolean validatorCsvObject(@RequestBody CsvPersonOnValidator csvPerson) throws ParseException {
        return (
                emailValidator(csvPerson) &&
                jobTitleValidator(csvPerson) &&
                dateOfBirthValidator(csvPerson)
        );
    }

    public boolean jobTitleValidator(CsvPersonOnValidator csvPerson){
        String[] jobTitle = {
                "Haematologist", "Phytotherapist", "Building surveyor",
                "Insurance account manager", "Educational psychologist"};

        return Arrays.stream(jobTitle)
                .anyMatch(job ->
                        ( job.equals(csvPerson.getJobTitle()) || job.equals(csvPerson.getJobTitle2()) )
                );
    }

    public boolean dateOfBirthValidator(CsvPersonOnValidator csvPerson) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date baseDate = dateFormat.parse("1980-01-01");
        Date dateOfBirth = dateFormat.parse(csvPerson.getDateOfBirth());

        return dateOfBirth.after(baseDate);
    }

    public boolean emailValidator(CsvPersonOnValidator csvPerson){
        String email = csvPerson.getEmail();
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}
