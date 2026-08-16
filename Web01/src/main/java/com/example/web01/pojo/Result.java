package com.example.web01.pojo;

import lombok.Data;

@Data
public class Result {

    private static final Integer CODE_SUCCESS = 1;
    private static final Integer CODE_ERROR = 0;
    private static final String MSG_SUCCESS = "success";

    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据

    public static Result success() {
        return success(null);
    }

    public static Result success(Object object) {
        return of(CODE_SUCCESS, MSG_SUCCESS, object);
    }

    public static Result error(String msg) {
        return of(CODE_ERROR, msg, null);
    }

    private static Result of(Integer code, String msg, Object data) {
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

}
