package org.example.model;


public class ExcelSafetyDataOnValidator{

    private String Date;
    private String injuryLocation;
    private String gender;
    private String ageGroup;
    private String incidentType;
    private String daysLost;
    private String plant;
    private String reportType;
    private String shift;
    private String department;
    private String incidentCost;
    private String wkDay;
    private String month;
    private String year;

    public ExcelSafetyDataOnValidator() {
    }

    public ExcelSafetyDataOnValidator(String injuryLocation, String reportType) {
        this.injuryLocation = injuryLocation;
        this.reportType = reportType;
    }

    public String getInjuryLocation() {
        return injuryLocation;
    }

    public void setInjuryLocation(String injuryLocation) {
        this.injuryLocation = injuryLocation;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
