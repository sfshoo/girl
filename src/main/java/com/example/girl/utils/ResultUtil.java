package com.example.girl.utils;

import com.example.girl.domain.Result;
import com.example.girl.enums.ResultEnum;
import com.example.girl.service.GirlService;

public class ResultUtil {

    public static Result success(ResultEnum status, Object object) {
        Result result = new Result();
        result.setCode(status.getCode());
        result.setMessage(status.getMessage());
        result.setDate(object);
        return result;
    }

    public static Result success(ResultEnum status) {
        Result result = new Result();
        result.setCode(status.getCode());
        result.setMessage(status.getMessage());
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result error(ResultEnum status) {
        Result result = new Result();
        result.setCode(status.getCode());
        result.setMessage(status.getMessage());
        return result;
    }

    public static Result error(ResultEnum status, String string) {
        Result result = new Result();
        result.setCode(status.getCode());
        result.setMessage(status.getMessage() + string);
        return result;
    }
}
