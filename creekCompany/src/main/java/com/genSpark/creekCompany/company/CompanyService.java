package com.genSpark.creekCompany.company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo repo;

    public List<Company> getAll(){
        return (List<Company>) repo.findAll();
    }

    public Company getByName(String name){
        Optional<Company> company = repo.getByName(name);
        return company.orElse(null);
    }

    public Company save(Company company){
        repo.save(company);
        return company;
    }

    public Company getById(long id){
        return repo.findById(id).orElse(null);
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }
}
