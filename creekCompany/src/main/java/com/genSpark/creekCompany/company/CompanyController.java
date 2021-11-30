package com.genSpark.creekCompany.company;
import com.genSpark.creekCompany.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
    @Autowired
    CompanyService service;

    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody Company company){
        return new ResponseEntity<>(service.save(company), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCompany(@PathVariable long id){
        Company company = service.getById(id);
        if(company == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new Custom(company.getName(), company.getEmployeeList()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


class Custom {
    private String name;
    private List<Employee> list;

    public Custom(String name, List<Employee> list){
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
