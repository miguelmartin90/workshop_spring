package org.example.service;

import org.example.feignCommunication.IServiceComValidator;
import org.example.model.CsvPerson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.model.FileMetadata;
import org.example.producer.ReaderRequestPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvFileProcessService {

    private final IServiceComValidator serviceComValidator;
    private final FileMetadata file = new FileMetadata();
    private final ReaderRequestPublisher readerRequestPublisher;
    List<CsvPerson> csvPeopleList;

    @Autowired
    public CsvFileProcessService(IServiceComValidator serviceComValidator, ReaderRequestPublisher readerRequestPublisher) {
        this.serviceComValidator = serviceComValidator;
        this.readerRequestPublisher = readerRequestPublisher;
    }

    public List<CsvPerson> csvFileReader(String filePath) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> dataCsv = reader.readAll();

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
        return csvPeopleList;
    }


    public FileMetadata sendCsvObject(List<CsvPerson> csvPeopleList){
        for(CsvPerson person: csvPeopleList){
            file.validatedLinesCounter(serviceComValidator.csvLineValidator(person));
        }
        return file;
    }

    public void sendCsvObjectWithRabbit(List<CsvPerson> csvPeopleList){
        for(CsvPerson person: csvPeopleList){
            readerRequestPublisher.produceOrder(person);
        }
    }

    public String testServiceFileProcess() {
        return "Response Process File: Respuesta desde método testServiceFileProcess()";
    }
}

