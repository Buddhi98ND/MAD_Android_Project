package com.example.employeemanagement;

import java.io.Serializable;

public class Salary implements Serializable {

    private String ID;
    private String jobTitle;
    private Double basicSalary;
    private Double otRate;
    private Double deduction;


    public Salary() {
    }

    public Salary(String ID, String jobTitle, Double basicSalary, Double otRate, Double deduction) {
        this.ID = ID;
        this.jobTitle = jobTitle;
        this.basicSalary = basicSalary;
        this.otRate = otRate;
        this.deduction = deduction;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getOtRate() {
        return otRate;
    }

    public void setOtRate(Double otRate) {
        this.otRate = otRate;
    }

    public Double getDeduction() {
        return deduction;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }
}
