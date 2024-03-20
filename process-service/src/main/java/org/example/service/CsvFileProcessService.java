package org.example.service;

import com.opencsv.CSVParser;
import org.example.response.CsvPerson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CsvFileProcessService {

    private ModelMapper mapper;
    public List<CsvPerson> csvFileReader(String filePath) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> dataCsv = reader.readAll();
        dataCsv.forEach(fileLine -> System.out.println(Arrays.toString(fileLine)));
        System.out.println("\nTamaño del array...: " + dataCsv.size());

        List<CsvPerson> csvPeopleList = dataCsv.stream()
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
                    return new CsvPerson(index, userId, firstName, lastName, sex, email, phone, dateOfBirth, jobTitle);
                })
                .collect(Collectors.toList());

        System.out.println("\nTamaño del array...: " + csvPeopleList.size());
//        fileLinesArray.forEach(fileLine -> System.out.println(Arrays.toString(fileLine)));

        return csvPeopleList;
    }
}

