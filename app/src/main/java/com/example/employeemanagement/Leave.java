package com.example.employeemanagement;

public class Leave {

    String name;
    String department;
    String startDate;
    String endDate;
    String Description;




    public Leave() {
    }

    public Leave(String name, String department, String startDate, String endDate , String Description) {
        this.name = name;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Description = Description;




    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



}
