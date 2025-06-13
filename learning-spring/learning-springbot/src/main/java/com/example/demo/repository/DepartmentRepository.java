package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Department;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}