package org.example.service;

import org.example.response.FileProcess;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileProcessService {

    private List<FileProcess> fileReaders;

    private ModelMapper mapper;

    public FileProcessService() {
        this.fileReaders = new ArrayList<>();
    }

    public List<String[]> csvFileReader(FileProcess fileReader) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(fileReader.getPath()));
        List<String[]> fileLinesArray = reader.readAll();
        fileLinesArray.forEach(fileLine -> System.out.println(Arrays.toString(fileLine)));
        System.out.println("\nTamaño del array...: " + fileLinesArray.size());

        return fileLinesArray;
    }
}
