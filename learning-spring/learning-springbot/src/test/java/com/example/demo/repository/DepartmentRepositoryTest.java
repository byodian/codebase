package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void testSaveAndFindById() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("HQ")
                .departmentCode("D001")
                .build();
        Department saved = departmentRepository.save(department);
        assertThat(saved.getDepartmentId()).isNotNull();
        assertThat(departmentRepository.findById(saved.getDepartmentId())).isPresent();
    }

    @Test
    void testDelete() {
        Department department = Department.builder()
                .departmentName("HR")
                .departmentAddress("Branch")
                .departmentCode("D002")
                .build();
        Department saved = departmentRepository.save(department);
        departmentRepository.deleteById(saved.getDepartmentId());
        assertThat(departmentRepository.findById(saved.getDepartmentId())).isNotPresent();
    }
}
