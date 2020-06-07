package com.yifan.spring.boot.mybatis.xml.model;

import org.springframework.stereotype.Component;

@Component
public class Course {

    private Integer id;
    private String name;
    private Integer teacherId;

    public Course() {
    }

    public Course(Integer id, String name, int teacherName) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
