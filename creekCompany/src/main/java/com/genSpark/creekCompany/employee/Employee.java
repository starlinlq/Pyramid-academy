package com.genSpark.creekCompany.employee;

import com.genSpark.creekCompany.company.Company;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 3)
    @Column
    private String name;

    @Range(min = 18, max = 60)
    @Column
    private int age;

    @Column
    LocalDateTime dateHired;

    public Employee(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDateTime dateHired) {
        this.dateHired = dateHired;
    }
}
