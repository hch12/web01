package com.example.web01.controller;

import com.example.web01.pojo.Dept;
import com.example.web01.pojo.Result;
import com.example.web01.service.DeptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DeptControllerTest {

    @Mock
    private DeptService deptService;

    @InjectMocks
    private DeptController deptController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deptController).build();
    }

    @Test
    void listWrapsServiceResultInSuccess() {
        List<Dept> depts = List.of(new Dept(1, "研发部", LocalDateTime.now(), LocalDateTime.now()));
        when(deptService.findAll()).thenReturn(depts);

        Result result = deptController.list();

        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(depts, result.getData());
        verify(deptService).findAll();
    }

    @Test
    void getDeptsReturnsSerializedDepartments() throws Exception {
        when(deptService.findAll())
                .thenReturn(List.of(new Dept(1, "研发部", LocalDateTime.of(2024, 1, 1, 0, 0), null)));

        mockMvc.perform(get("/depts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("研发部"));
    }

    @Test
    void getDeptsReturnsEmptyDataWhenNoDepartments() throws Exception {
        when(deptService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/depts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void deleteReturnsSuccessWithoutData() {
        Result result = deptController.delete(7);

        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
        verifyNoInteractions(deptService);
    }

    @Test
    void deleteDeptEndpointRequiresIdParameter() throws Exception {
        mockMvc.perform(delete("/depts"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(delete("/depts").param("id", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
    }
}
