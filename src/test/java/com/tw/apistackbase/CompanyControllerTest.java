package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;


    @Test
    public void should_return_companies_when_invoke_getAllTest() throws Exception {

        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "alibaba", 244));

        when(companyService.getAll()).thenReturn(companies);
        ResultActions resultActions = mockMvc.perform(get("/companies"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyName", is("alibaba")))
                .andExpect(jsonPath("$.[0].employeesNumber", is(244)));

    }

    @Test
    public void should_return_companies_when_invoke_getById_given_companyId() throws Exception {

        Company company = new Company(1, "alibaba", 244);

        when(companyService.getById(anyInt())).thenReturn(company);

        ResultActions resultActions = mockMvc.perform(get("/companies/{companyId}", company.getId()));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.companyName", is("alibaba")))
                .andExpect(jsonPath("$.employeesNumber", is(244)));
    }

    @Test
    public void should_return_employees_when_invoke_getEmployeesByCompanyId_given_companyId() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "mike", 13, "male", 5600, 1));

        Company company = new Company();

        when(companyService.getEmployeesByCompanyId(anyInt())).thenReturn(employees);

        ResultActions resultActions = mockMvc.perform(get("/companies/{companyId}/employees", company.getId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("mike")))
                .andExpect(jsonPath("$[0].age", is(13)))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[0].salary", is(5600)));
    }

    @Test
    public void should_return_companies_when_invoke_getByPaging() throws Exception {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "alibaba1", 244));
        companies.add(new Company(2, "alibaba2", 245));
        companies.add(new Company(3, "alibaba3", 246));
        companies.add(new Company(4, "alibaba4", 247));
        companies.add(new Company(5, "alibaba5", 248));

        when(companyService.getByPaging(anyInt(), anyInt())).thenReturn(companies);

        ResultActions resultActions = mockMvc.perform(get("/companies").
                contentType(MediaType.APPLICATION_JSON).param("page", "1").param("pageSize", "5"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void should_return_new_company_when_invoke_add() throws Exception {

        when(companyService.addCompany(any(Company.class))).thenReturn(new Company(1, "alibaba", 244));

        ResultActions resultActions = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"id\": 1,\n" +
                        "        \"companyName\": \"alibaba\",\n" +
                        "        \"employeesNumber\": 244\n" +
                        "    }"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.companyName", is("alibaba")))
                .andExpect(jsonPath("$.employeesNumber", is(244)));
    }

    @Test
    public void should_return_update_company_when_invoke_update() throws Exception {

        Company company = new Company(1, "alibaba", 244);

        when(companyService.update(anyInt(),any(Company.class))).thenReturn(company);

        ResultActions resultActions = mockMvc.perform(put("/companies/{companyId}",company.getId()).contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                "        \"id\": 1,\n" +
                "        \"companyName\": \"alibaba\",\n" +
                "        \"employeesNumber\": 300\n" +
                "    }"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.companyName", is("alibaba")))
                .andExpect(jsonPath("$.employeesNumber", is(244)));
    }

    @Test
    public void invoke_deleteById_given_companyId() throws Exception {

        ResultActions resultActions = mockMvc.perform(delete("/companies/{companyId}",1).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());

    }
}
