package com.yifan.spring.boot.mybatis.xml.model;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Teacher {

    private Integer id;
    private String name;
    private Set<Course> courses;

    public Teacher() {
    }

    public Teacher(int id, String name, Set<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
