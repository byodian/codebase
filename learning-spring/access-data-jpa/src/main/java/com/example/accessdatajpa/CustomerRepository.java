package com.example.accessdatajpa;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    // You need not write an implementation of the repository interface.
    // Spring Data JPA creates an implementation when you run the application.
    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
}