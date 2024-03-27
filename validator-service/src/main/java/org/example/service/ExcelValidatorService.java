package org.example.service;

import org.example.model.ExcelSafetyDataOnValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Service
public class ExcelValidatorService {

    public boolean validatorExcelObject(@RequestBody ExcelSafetyDataOnValidator excelSafetyData){
        return (
                injuryLocation(excelSafetyData) &&
                        reportType(excelSafetyData)
        );
    }

    public boolean injuryLocation(ExcelSafetyDataOnValidator excelSafetyData){
        return (!excelSafetyData.getInjuryLocation().contains("N/A"));
    }

    public boolean reportType(ExcelSafetyDataOnValidator excelSafetyData){
        String[] reportTypeValid = {"Near Miss", "Lost Time", "First Aid"};

        return Arrays.stream(reportTypeValid)
                .anyMatch(job ->
                        ( job.equals(excelSafetyData.getReportType()))
                );
    }
}
