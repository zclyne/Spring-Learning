package com.yifan.spring.rabbitmq.demo;

public class MyMessage {

    private String id;
    private String msg;

    // Jackson需要无参构造器来实现反序列化
    public MyMessage() {
    }

    public MyMessage(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
