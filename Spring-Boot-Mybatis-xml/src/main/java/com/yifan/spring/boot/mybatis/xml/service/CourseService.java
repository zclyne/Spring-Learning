package com.yifan.spring.boot.mybatis.xml.service;

import com.yifan.spring.boot.mybatis.xml.mapper.CourseMapper;
import com.yifan.spring.boot.mybatis.xml.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    public List<Course> listCourses() {
        return courseMapper.listCourses();
    }

    public Course getCourseById(int id) {
        return courseMapper.getCourseById(id);
    }

    public Course getCourseByColumn(String column, String value) {
        return courseMapper.getCourseByColumn(column, value);
    }

    public int insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    public int deleteCourse(Course course) {
        return courseMapper.deleteCourseById(course.getId());
    }

}
