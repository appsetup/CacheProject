package com.naveen.test;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 1/3/11
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Department
{
    private long departmentId;
    private String departmentName;

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
