package com.yifan.spring.boot.mybatis.xml.controller;

import com.yifan.spring.boot.mybatis.xml.model.ResponseBean;
import com.yifan.spring.boot.mybatis.xml.model.Teacher;
import com.yifan.spring.boot.mybatis.xml.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseBean getTeacher(@RequestParam(required = false) String id, @RequestParam(required = false) String name) {
        if (id != null && !"".equals(id)) {
            return ResponseBean.success("Get teacher by id", teacherService.getTeacherById(Integer.parseInt(id)));
        } else if (name != null && !"".equals(name)) {
            return ResponseBean.success("Get teacher by name", teacherService.getTeacherByColumn("name", name));
        } else {
            return ResponseBean.success("List all teachers", teacherService.listTeachers());
        }
    }

    @PostMapping("/teachers")
    public ResponseBean insertTeacher(@RequestBody Teacher teacher) {
        int result = teacherService.insertTeacher(teacher);
        if (result == 1) {
            return ResponseBean.success("Successfully inserted one teacher", teacherService.getTeacherByColumn("name", teacher.getName()));
        } else {
            return ResponseBean.error("Failed to insert teacher", null);
        }
    }

    @PutMapping("/teachers")
    public ResponseBean updateTeacher(@RequestBody Teacher teacher) {
        int result = teacherService.updateTeacher(teacher);
        if (result == 1) {
            return ResponseBean.success("Successfully updated the teacher", teacherService.getTeacherById(teacher.getId()));
        } else {
            return ResponseBean.error("Failed to update the teacher", null);
        }
    }

    @DeleteMapping("/teachers")
    public ResponseBean deleteTeacher(@RequestBody Teacher teacher) {
        int result = teacherService.deleteTeacher(teacher);
        if (result == 1) {
            return ResponseBean.success("Successfully delete the teacher", null);
        } else {
            return ResponseBean.error("Failed to delete the teacher", null);
        }
    }

}
