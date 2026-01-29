package com.example.web01.controller;

import com.example.web01.pojo.Dept;
import com.example.web01.pojo.Result;
import com.example.web01.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    @GetMapping("/depts")
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId){
        System.out.println("根据ID删除部门: " + deptId);
        return Result.success();
    }
}
