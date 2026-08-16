package com.example.web01.service;

import com.example.web01.exception.DeptNotFoundException;
import com.example.web01.mapper.DeptMapper;
import com.example.web01.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("部门ID不能为空");
        }
        if (deptMapper.deleteById(id) == 0) {
            throw new DeptNotFoundException(id);
        }
    }
}
