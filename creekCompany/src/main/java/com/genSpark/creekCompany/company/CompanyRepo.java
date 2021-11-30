package com.genSpark.creekCompany.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompanyRepo extends CrudRepository<Company, Long> {
    public Optional<Company> getByName(String name);
}
