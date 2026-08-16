package com.example.web01.service;

import com.example.web01.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> findAll();

    /**
     * 根据ID删除部门，记录不存在时抛出DeptNotFoundException
     */
    public void deleteById(Integer id);
}
