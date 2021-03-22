package com.example.girl.enums;

import com.example.girl.utils.ResultUtil;

public enum ResultEnum {
    UNKNOW_ERROR(-1, "Unknow Error!"),
    SUCCESS(0, "Success!"),
    PRIMARY_SCHOOL(100, "Primary School"),
    MIDDLE_SCHOOL(101, "Middle School"),
    FIELD_ERROR(103,"Field Error:"),
    OBJECT_ERROR(104,"Object Error"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
