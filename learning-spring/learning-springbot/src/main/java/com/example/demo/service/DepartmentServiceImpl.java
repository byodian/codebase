package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department, Long departmentId) {
        Optional<Department> deptOptional = departmentRepository.findById(departmentId);
        if (deptOptional.isPresent()) {
            Department depDB = deptOptional.get();

            if (Objects.nonNull(department.getDepartmentName())
                && !"".equalsIgnoreCase(department.getDepartmentName())) {
                depDB.setDepartmentName(department.getDepartmentName());
            }

            if (Objects.nonNull(department.getDepartmentAddress())
                && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
                depDB.setDepartmentAddress(department.getDepartmentAddress());
            }

            if (Objects.nonNull(department.getDepartmentCode())
                && !"".equalsIgnoreCase(department.getDepartmentCode())) {
                depDB.setDepartmentCode(department.getDepartmentCode());
            }

            return departmentRepository.save(depDB);
        } else {
            throw new RuntimeErrorException(null);
        }
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
