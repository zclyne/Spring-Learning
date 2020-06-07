package com.yifan.spring.boot.mybatis.xml.service;

import com.yifan.spring.boot.mybatis.xml.mapper.TeacherMapper;
import com.yifan.spring.boot.mybatis.xml.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public List<Teacher> listTeachers() {
        return teacherMapper.listTeachers();
    }

    public Teacher getTeacherById(int id) {
        return teacherMapper.getTeacherById(id);
    }

    public Teacher getTeacherByColumn(String column, String value) {
        return teacherMapper.getTeacherByColumn(column, value);
    }

    public int insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    public int deleteTeacher(Teacher teacher) {
        return teacherMapper.deleteTeacherById(teacher.getId());
    }

}
