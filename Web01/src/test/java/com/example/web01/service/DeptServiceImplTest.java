package com.example.web01.service;

import com.example.web01.mapper.DeptMapper;
import com.example.web01.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeptServiceImplTest {

    @Mock
    private DeptMapper deptMapper;

    @InjectMocks
    private DeptServiceImpl deptService;

    @Test
    void findAllReturnsMapperResult() {
        List<Dept> depts = List.of(new Dept(1, "研发部", LocalDateTime.now(), LocalDateTime.now()));
        when(deptMapper.findAll()).thenReturn(depts);

        List<Dept> actual = deptService.findAll();

        assertSame(depts, actual);
        assertEquals(1, actual.size());
        verify(deptMapper).findAll();
    }

    @Test
    void findAllReturnsEmptyListWhenNoDepartments() {
        when(deptMapper.findAll()).thenReturn(List.of());

        assertTrue(deptService.findAll().isEmpty());
        verify(deptMapper).findAll();
    }

    @Test
    void findAllPropagatesMapperException() {
        RuntimeException failure = new RuntimeException("db down");
        when(deptMapper.findAll()).thenThrow(failure);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> deptService.findAll());

        assertSame(failure, thrown);
    }
}
