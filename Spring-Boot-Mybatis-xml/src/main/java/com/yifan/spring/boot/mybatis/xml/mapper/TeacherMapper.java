package com.yifan.spring.boot.mybatis.xml.mapper;

import com.yifan.spring.boot.mybatis.xml.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<Teacher> listTeachers();

    Teacher getTeacherById(@Param("id") int id);

    Teacher getTeacherByColumn(@Param("column") String column, @Param("value") String value);

    int insertTeacher(@Param("teacher") Teacher teacher);

    int updateTeacher(@Param("teacher") Teacher teacher);

    int deleteTeacherById(@Param("id") Integer id);

}
