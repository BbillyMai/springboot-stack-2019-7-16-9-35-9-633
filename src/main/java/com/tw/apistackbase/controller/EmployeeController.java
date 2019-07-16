package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getById(@PathVariable int employeeId) {
        return employeeService.getById(employeeId);
    }


    @GetMapping(value = "/employees", params = {"page", "pageSize"})
    public List<Employee> getByPaging(int page, int pageSize) {
        return employeeService.getByPaging(page, pageSize);
    }

    @GetMapping(value="/employees",params = "gender")
    public List<Employee> getByGender(String gender){
        return employeeService.getByGender(gender);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee update(@RequestBody Employee employee, @PathVariable int employeeId) {
        return employeeService.update(employeeId, employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public Employee deleteById(@PathVariable int employeeId) {
        return employeeService.deleteById(employeeId);
    }
}
