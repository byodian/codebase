package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDepartment() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("HQ")
                .departmentCode("D001")
                .build();
        when(departmentRepository.save(department)).thenReturn(department);
        Department saved = departmentService.saveDepartment(department);
        assertEquals(department, saved);
    }

    @Test
    void testFetchDepartmentList() {
        Department d1 = Department.builder().departmentId(1L).departmentName("IT").build();
        Department d2 = Department.builder().departmentId(2L).departmentName("HR").build();
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(d1, d2));
        List<Department> list = departmentService.fetchDepartmentList();
        assertEquals(2, list.size());
        assertEquals("IT", list.get(0).getDepartmentName());
    }

    @Test
    void testUpdateDepartment() {
        Department oldDept = Department.builder().departmentId(1L).departmentName("Old").build();
        Department newDept = Department.builder().departmentName("New").build();
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(oldDept));
        when(departmentRepository.save(any(Department.class))).thenReturn(newDept);
        Department updated = departmentService.updateDepartment(newDept, 1L);
        assertEquals("New", updated.getDepartmentName());
    }

    @Test
    void testDeleteDepartmentById() {
        departmentService.deleteDepartmentById(1L);
        verify(departmentRepository, times(1)).deleteById(1L);
    }
}
