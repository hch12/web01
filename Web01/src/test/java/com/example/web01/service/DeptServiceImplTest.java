package com.example.web01.service;

import com.example.web01.exception.DeptNotFoundException;
import com.example.web01.mapper.DeptMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeptServiceImplTest {

    @Mock
    private DeptMapper deptMapper;

    @InjectMocks
    private DeptServiceImpl deptService;

    @Test
    void deleteByIdDeletesExistingDept() {
        when(deptMapper.deleteById(1)).thenReturn(1);

        deptService.deleteById(1);

        verify(deptMapper).deleteById(1);
    }

    @Test
    void deleteByIdThrowsWhenDeptMissing() {
        when(deptMapper.deleteById(99)).thenReturn(0);

        assertThrows(DeptNotFoundException.class, () -> deptService.deleteById(99));
    }

    @Test
    void deleteByIdRejectsNullId() {
        assertThrows(IllegalArgumentException.class, () -> deptService.deleteById(null));
    }
}
