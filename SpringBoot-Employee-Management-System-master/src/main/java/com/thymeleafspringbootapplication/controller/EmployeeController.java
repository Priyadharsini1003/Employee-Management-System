package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Tag(name = "Employee", description = "Employee Management APIs")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "View Home Page", description = "View the home page with the list of employees")
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @Operation(summary = "Show New Employee Form", description = "Display the form to add a new employee")
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @Operation(summary = "Save Employee", description = "Save a new employee")
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @Operation(summary = "Show Form For Update", description = "Display the form to update an existing employee")
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@Parameter(description = "ID of the employee to be updated") @PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @Operation(summary = "Delete Employee", description = "Delete an existing employee by ID")
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@Parameter(description = "ID of the employee to be deleted") @PathVariable(value = "id") long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
