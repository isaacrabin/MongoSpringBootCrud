package com.example.question3.Repository;

import com.example.question3.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends MongoRepository<Employee, Integer> {
}
