package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/companies/{companyId}")
    public Company getById(@PathVariable int companyId) {
        return companyService.getById(companyId);
    }

    @GetMapping("/companies/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int companyId) {
        return companyService.getEmployeesByCompanyId(companyId);
    }

    @GetMapping(value = "/companies", params = {"page", "pageSize"})
    public List<Company> getByPaging(int page, int pageSize) {
        return companyService.getByPaging(page, pageSize);
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        return new ResponseEntity<Company>(companyService.addCompany(company), HttpStatus.CREATED);
    }

    @PutMapping("/companies/{companyId}")
    public Company update(@RequestBody Company company, @PathVariable int companyId) {
        return companyService.update(companyId, company);
    }

    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<Void> deleteById(@PathVariable int companyId) {
        companyService.deleteById(companyId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
