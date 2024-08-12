package com.example.accessdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// At runtime, Spring Data Rest automatically creates an implementation of this interface.
// Then it uses the @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people.
// Spring Boot automatically spins up Spring Data JPA to create a concrete implementation of the repository.
// and configure it to talk to a back end in-memory database by using JPA.

// Spring Data REST builds on top of Spring MVC. It creates a collection of Spring MVC controllers, JSON converters,
// and other beans to provide a RESTful front end.
// RepositoryRestMvcConfiguration
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {
    List<Person> findByLastName(@Param("name") String name);
}
