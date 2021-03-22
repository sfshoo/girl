package com.example.girl.domain;

public class Result<T> {
    // 错误码
    private Integer code;
    // 提示信息
    private String message;
    // 具体返回内容
    private T date;

    @Override
    public String   toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }


}
