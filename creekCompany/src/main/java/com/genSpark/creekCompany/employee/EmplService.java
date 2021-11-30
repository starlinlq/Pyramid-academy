package com.genSpark.creekCompany.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmplService {
    @Autowired
    EmplRepo repo;

    public Employee save(Employee employee){
        repo.save(employee);
        return employee;
    }
}
