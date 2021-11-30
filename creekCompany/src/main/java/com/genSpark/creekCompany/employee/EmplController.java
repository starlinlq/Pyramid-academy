package com.genSpark.creekCompany.employee;

import com.genSpark.creekCompany.company.Company;
import com.genSpark.creekCompany.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmplController {

    @Autowired
    EmplService service;

    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Custom custom){
        Company company = companyService.getByName(custom.getCompanyName());
        Employee employee = custom.getEmployee();
        employee.setDateHired(LocalDateTime.now());

        if (company != null){
           company.getEmployeeList().add(employee);
           return ResponseEntity.ok().body(service.save(employee));
        }

        return ResponseEntity.badRequest().body(null);
    }

}

class Custom {
    private String companyName;
    private Employee employee;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
