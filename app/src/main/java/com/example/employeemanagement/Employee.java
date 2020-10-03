package com.example.employeemanagement;

public class Employee {

    private String empName;
    private String empID;
    private String department;
    private String email;
    private String phone;

    public Employee() {
    }

    public Employee(String empName, String empID, String department, String email, String phone) {
        this.empName = empName;
        this.empID = empID;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
