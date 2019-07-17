package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private static Map<Integer, Employee> employees = new HashMap<>();

    static {
        employees.put(1, new Employee(1, "xiaohong", 10, "female", 4000, 1));
        employees.put(2, new Employee(2, "xiaowang", 17, "male", 5000, 1));
        employees.put(3, new Employee(3, "xiaoli", 13, "female", 7000, 1));
        employees.put(4, new Employee(4, "xiaozhang", 20, "male", 8000, 1));
    }

    public List<Employee> getByCompanyId(int companyId) {
        return new ArrayList<>(employees.values()).stream().
                filter(employee -> employee.getCompanyId() == companyId).collect(Collectors.toList());
    }

    public List<Employee> getAll() {
        return mapToList();
    }

    public Employee getById(int employeeId) {
        return employees.get(employeeId);
    }

    public List<Employee> getByPaging(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = page * pageSize > employees.size() ? employees.size() : page * pageSize;
        return mapToList().subList(startIndex, endIndex);
    }

    public Employee addEmployee(Employee employee) {
        employees.put(employee.getId(),employee);
        return employee;
    }

    public Employee update(int employeeId, Employee employee) {
        Employee updateEmployee = employees.get(employeeId);
        updateEmployee.setId(employee.getId());
        updateEmployee.setName(employee.getName());
        updateEmployee.setAge(employee.getAge());
        updateEmployee.setGender(employee.getGender());
        updateEmployee.setSalary(employee.getSalary());
        updateEmployee.setCompanyId(employee.getCompanyId());
        return updateEmployee;
    }

    public Employee deleteById(int employeeId) {
        return employees.remove(employeeId);
    }

    private List<Employee> mapToList() {
        return new ArrayList<Employee>(employees.values());
    }

    public List<Employee> getByGender(String gender) {
        return mapToList().stream().
                filter(employee -> gender.equals(employee.getGender())).collect(Collectors.toList());
    }
}
