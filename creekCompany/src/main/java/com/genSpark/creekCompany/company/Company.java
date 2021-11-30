package com.genSpark.creekCompany.company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.genSpark.creekCompany.employee.Employee;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private long id;

    @Size(min=3)
    @Column
    private String name;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "EMPLOYEE_ID")
    List<Employee> employeeList = new ArrayList<>();

    public Company(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
