package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    public Employee getById(int employeeId) {
        return employeeRepository.getById(employeeId);
    }

    public List<Employee> getByPaging(int page, int pageSize) {
        return employeeRepository.getByPaing(page,pageSize);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee update(int employeeId, Employee employee) {
        return employeeRepository.update(employeeId,employee);
    }

    public Employee deleteById(int employeeId) {
        return employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.getByGender(gender);
    }
}
