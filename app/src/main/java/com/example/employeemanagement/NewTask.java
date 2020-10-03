package com.example.employeemanagement;

public class NewTask {
    private String taskNumber,task,description,startDate,endDate,employee;

    public NewTask( )
    {

    }

    public NewTask( String taskNumber,String task, String description, String startDate, String endDate, String employee) {

        this.taskNumber = taskNumber;
        this.task = task;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }


    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }


}

