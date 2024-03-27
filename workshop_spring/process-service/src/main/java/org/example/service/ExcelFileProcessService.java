package org.example.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.ExcelSafetyData;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
//Expresion regular
@Service
public class ExcelFileProcessService {

    public List<ExcelSafetyData> excelFileReader(String path) throws IOException {

        FileInputStream excelFile = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(path);

        for(Sheet sheet: workbook){
            System.out.println(sheet.getSheetName());

            for(Row row: sheet){
                System.out.println(row);
            }
        }
        return null;
    }
}
