package org.example.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.example.client.IServiceComValidator;
import org.example.model.CsvPerson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.model.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvFileProcessService {

    private IServiceComValidator serviceComValidator;
    private final FilePath file = new FilePath();

    @Autowired
    public CsvFileProcessService(IServiceComValidator serviceComValidator) {
        this.serviceComValidator = serviceComValidator;
    }

    /*public CsvFileProcessService(IServiceComValidator serviceComValidator) {
        this.serviceComValidator = serviceComValidator;
    }*/

    List<CsvPerson> csvPeopleList;

    public List<CsvPerson> csvFileReader(String filePath) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> dataCsv = reader.readAll();
        dataCsv.forEach(fileLine -> System.out.println(Arrays.toString(fileLine)));
        System.out.println("\nTamaño del array...: " + dataCsv.size());

        csvPeopleList = dataCsv.stream()
                .skip(1)
                .map(row -> {
                        String index = row[0];
                        String userId = row[1];
                        String firstName = row[2];
                        String lastName = row[3];
                        String sex = row[4];
                        String email = row[5];
                        String phone = row[6];
                        String dateOfBirth = row[7];
                        String jobTitle = row[8];
                        String jobTitle2 = row.length > 9 ? row[9]: "";
                        return new CsvPerson(index, userId, firstName, lastName, sex, email, phone, dateOfBirth, jobTitle, jobTitle2);

                })
                .collect(Collectors.toList());

//        System.out.println("\nTamaño del array...: " + csvPeopleList.size());
//        fileLinesArray.forEach(fileLine -> System.out.println(Arrays.toString(fileLine)));
//        return csvPeopleList.forEach(CsvPerson::new);
        return csvPeopleList;
    }

    public void validatedLinesCounter(boolean validatedLine){
        if (validatedLine){
            file.setLineValid();
        } else {
            file.setLineInvalid();
        }
    }
    public FilePath sendObject(List<CsvPerson> csvPeopleList){
        for(CsvPerson person: csvPeopleList){
            validatedLinesCounter(serviceComValidator.csvLineValidator(person));
        }
        return file;
    }

    public String testServiceFileProcess() {
        return "Response Process File: Respuesta desde método testServiceFileProcess()";
    }
}

