package com.yifan.spring.boot.mybatis.xml.controller;

import com.yifan.spring.boot.mybatis.xml.model.Course;
import com.yifan.spring.boot.mybatis.xml.model.ResponseBean;
import com.yifan.spring.boot.mybatis.xml.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    public ResponseBean getCourse(@RequestParam(required = false) String id, @RequestParam(required = false) String name) {
        if (id != null && !"".equals(id)) {
            return ResponseBean.success("Get course by id", courseService.getCourseById(Integer.parseInt(id)));
        } else if (name != null && !"".equals(name)) {
            return ResponseBean.success("Get course by name", courseService.getCourseByColumn("name", name));
        } else {
            return ResponseBean.success("List all courses", courseService.listCourses());
        }
    }

    @PostMapping("/courses")
    public ResponseBean insertCourse(@RequestBody Course course) {
        int result = courseService.insertCourse(course);
        if (result == 1) {
            return ResponseBean.success("Successfully inserted one course", courseService.getCourseByColumn("name", course.getName()));
        } else {
            return ResponseBean.error("Failed to insert the course", null);
        }
    }

    @PutMapping("/courses")
    public ResponseBean updateCourse(@RequestBody Course course) {
        int result = courseService.updateCourse(course);
        if (result == 1) {
            return ResponseBean.success("Successfully updated the course", courseService.getCourseById(course.getId()));
        } else {
            return ResponseBean.error("Failed to update the course", null);
        }
    }

    @DeleteMapping("/courses")
    public ResponseBean deleteCourse(@RequestBody Course course) {
        int result = courseService.deleteCourse(course);
        if (result == 1) {
            return ResponseBean.success("Successfully delete the course", null);
        } else {
            return ResponseBean.error("Failed to delete the course", null);
        }
    }

}
