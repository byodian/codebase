package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveDepartment() throws Exception {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("HQ")
                .departmentCode("D001")
                .build();
        Mockito.when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("IT"));
    }

    @Test
    void testFetchDepartmentList() throws Exception {
        Department d1 = Department.builder().departmentId(1L).departmentName("IT").build();
        Department d2 = Department.builder().departmentId(2L).departmentName("HR").build();
        Mockito.when(departmentService.fetchDepartmentList()).thenReturn(Arrays.asList(d1, d2));
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departmentName").value("IT"));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        Department department = Department.builder().departmentName("Updated").build();
        Mockito.when(departmentService.updateDepartment(any(Department.class), anyLong())).thenReturn(department);
        mockMvc.perform(put("/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("Updated"));
    }

    @Test
    void testDeleteDepartmentById() throws Exception {
        Mockito.doNothing().when(departmentService).deleteDepartmentById(1L);
        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));
    }
}
