package com.genSpark.creekCompany.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmplRepo extends CrudRepository<Employee, Long> {
}
