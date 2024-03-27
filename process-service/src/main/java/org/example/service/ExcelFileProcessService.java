package org.example.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.client.IServiceComValidator;
import org.example.model.ExcelSafetyData;
import org.example.model.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.*;
import java.util.stream.Collectors;

@Service
public class ExcelFileProcessService {

    private final IServiceComValidator serviceComValidator;

    private List<ExcelSafetyData> excelSafetyData;
    List<String[]> dataXlsx = new ArrayList<>();
    private final FileMetadata file = new FileMetadata();

    @Autowired
    public ExcelFileProcessService(IServiceComValidator serviceComValidator) {
        this.serviceComValidator = serviceComValidator;
    }

    public List<ExcelSafetyData> excelFileReader(String filePath) throws IOException {

            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {
                int i = 0;
                String[] dataList = new String[14];

                for (Cell cell : row) {
                    String cellValue = null;
                    switch (cell.getCellType()) {
                        case FORMULA:
                            // Obtener el valor de la celda evaluando la fórmula
                            CellValue cellEval = evaluator.evaluate(cell);
                            cellValue = formatter.formatCellValue(cell, evaluator);
                            break;
                        default:
                            cellValue = formatter.formatCellValue(cell);
                    }
                    dataList[i] = cellValue;
                    i++;
                }
                dataXlsx.add(dataList);
            }

            excelSafetyData = dataXlsx.stream()
                    .skip(1)
                    .map(row -> {
                                String injuryLocation = row[1];
                                String reportType = row[7];
                                return new ExcelSafetyData(injuryLocation, reportType);
                            })
                    .collect(Collectors.toList());

            file.close();
            workbook.close();
        return excelSafetyData;
    }

    public FileMetadata sendExcelObject(List<ExcelSafetyData> excelSafetyDataList){
        for(ExcelSafetyData excelSafetyData1: excelSafetyDataList){
            file.validatedLinesCounter(serviceComValidator.excelLineValidator(excelSafetyData1));
        }
        return file;
    }
}



