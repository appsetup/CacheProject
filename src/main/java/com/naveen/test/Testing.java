package com.naveen.test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.naveen.test.query.Query;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 27/2/11
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Testing
{
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        CacheManager cacheManager = classPathXmlApplicationContext.getBean("cacheManager", CacheManager.class);
        Department department = new Department();
        department.setDepartmentId(1l);
        department.setDepartmentName("First Department");

        Employee employee = new Employee();
        employee.setEmployeeId(1l);
        employee.setFirstName("Naveen");
        employee.setLastName("Kumtakar");
        employee.setEmployeeDepartment(department);
        cacheManager.addCache(employee);
        cacheManager.addCache(employee);
        BasicDBObject basicDBObject = new BasicDBObject("$gt",0);

//        query.put("employeeId", new BasicDBObject("$gt",1).append("$lt",2));
//        BasicDBObject basicDBObject = new BasicDBObject("$gt",5);
//        BasicDBObject basicDBObject1 = new BasicDBObject("$lt", 4);
//        List<BasicDBObject> list = new ArrayList<BasicDBObject>();
//        list.add(basicDBObject);
//        list.add(basicDBObject1);
//
//        BasicDBObject basicDBObject2 = new BasicDBObject("data",list);
//        BasicDBObject basicDBObject3 = new BasicDBObject("employeeId", basicDBObject);
//        BasicDBObject basicDBObject2 = new BasicDBObject("$lt",2);
//        BasicDBObject basicDBObject4 = new BasicDBObject("employeeId", basicDBObject2);
//        BasicDBObject basicDBObject5 = new BasicDBObject();
//        List<BasicDBObject>list = new ArrayList<BasicDBObject>();
//        list.add(basicDBObject3);
//        list.add(basicDBObject4);
//        basicDBObject5.put("$or", list);
//
//        BasicDBObject basicDBObject6 = new BasicDBObject("_id",0);

//        BasicDBObject query = new BasicDBObject();


//        String s = basicDBObject5.toString();
//        System.out.println("s = " + s);
        Query query = new Query();
//        query.field("employeeId")
//                .greaterThan("1")
//                .field("employeeId")
//                .lessThan("0");
        Query firstCondition = new Query();
        firstCondition.field("employeeId").greaterThanOrEqualTo("1");
        Query secondCondition = new Query();
        secondCondition.field("employeeDepartment.departmentId").equalTo("1");
        query.or(firstCondition, secondCondition);
        List<Employee> search = cacheManager.search(query, Employee.class);
        for (Employee employee1 : search) {
            System.out.println("employee1 = " + employee1);
        }
//        System.out.println("basicDBObject6 = " + basicDBObject6);
//        cacheManager.search(basicDBObject5,basicDBObject6,Employee.class);
//        cacheManager.deleteCache(employee);
//        System.out.println("dbConnectionPool = " + dbConnectionPool);
    }
}
