package com.example.web01.mapper;

import com.example.web01.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     */
    @Results({@Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")})
    @Select("select id, name, create_time, update_time from dept")
    public List<Dept> findAll();

}