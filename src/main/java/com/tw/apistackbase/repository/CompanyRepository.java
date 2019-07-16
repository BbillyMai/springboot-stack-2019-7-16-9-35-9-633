package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {

    private static Map<Integer, Company> companies = new HashMap<>();

    static {
        companies.put(1, new Company(1, "alibaba", 200));
        companies.put(2, new Company(2, "oocl", 300));
        companies.put(3, new Company(3, "baidu", 250));
        companies.put(4, new Company(4, "google", 250));
        companies.put(5, new Company(5, "biying", 250));
    }

    public List<Company> getAll() {
        return mapToList();
    }

    public Company getById(int companyId) {
        return companies.get(companyId);
    }


    public List<Company> getByPaging(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = page * pageSize > companies.size() ? companies.size() : page * pageSize;
        return mapToList().subList(startIndex, endIndex);
    }

    public Company addCompany(Company company) {
        companies.put(company.getId(),company);
        return company;
    }

    public void deleteById(int companyId) {
        companies.remove(companyId);
    }

    public Company update(int companyId, Company company) {
        Company updateCompany = companies.get(companyId);
        updateCompany.setId(company.getId());
        updateCompany.setCompanyName(company.getCompanyName());
        updateCompany.setEmployeesNumber(company.getEmployeesNumber());
        return updateCompany;
    }

    private List<Company> mapToList() {
        return new ArrayList<Company>(companies.values());
    }
}
