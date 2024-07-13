package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Employee updateEmployee(long id, Employee employeeDetails);
}
