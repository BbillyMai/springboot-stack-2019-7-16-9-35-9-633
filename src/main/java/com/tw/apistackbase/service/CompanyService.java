package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company getById(int companyId) {
        return companyRepository.getById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return employeeRepository.getByCompanyId(companyId);
    }

    public List<Company> getByPaging(int page, int pageSize) {
        return companyRepository.getByPaging(page, pageSize);
    }

    public Company addCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public Company update(int companyId, Company company) {
        return companyRepository.update(companyId,company);
    }

    public void deleteById(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
