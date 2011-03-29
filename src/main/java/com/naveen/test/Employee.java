package com.naveen.test;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 1/3/11
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Employee
{
    private long employeeId;
    private String firstName;
    private String lastName;
    private Department employeeDepartment;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(Department employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }
}
