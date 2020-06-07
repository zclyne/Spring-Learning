package com.yifan.spring.boot.mybatis.xml.model;

import org.springframework.stereotype.Component;

public class ResponseBean {

    private String status;
    private String message;
    private Object data;

    public static ResponseBean success(String message, Object data) {
        return new ResponseBean("success", message, data);
    }

    public static ResponseBean error(String message, Object data) {
        return new ResponseBean("error", message, data);
    }

    private ResponseBean(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
