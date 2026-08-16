package com.example.web01.exception;

/**
 * 部门不存在时抛出，避免对不存在的记录返回成功结果
 */
public class DeptNotFoundException extends RuntimeException {

    public DeptNotFoundException(Integer id) {
        super("部门不存在: " + id);
    }
}
