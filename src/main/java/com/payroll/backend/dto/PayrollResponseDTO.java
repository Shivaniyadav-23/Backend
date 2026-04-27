package com.payroll.backend.dto;

import com.payroll.backend.enums.SalaryType;

public class PayrollResponseDTO {

    private String employeeName;
    private String month;
    private int presentDays;
    private SalaryType salaryType;
    private double baseSalary;
    private double calculatedSalary;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(int presentDays) {
        this.presentDays = presentDays;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getCalculatedSalary() {
        return calculatedSalary;
    }

    public void setCalculatedSalary(double calculatedSalary) {
        this.calculatedSalary = calculatedSalary;
    }
}
