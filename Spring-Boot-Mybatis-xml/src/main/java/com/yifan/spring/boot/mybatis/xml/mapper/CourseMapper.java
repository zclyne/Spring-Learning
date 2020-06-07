package com.yifan.spring.boot.mybatis.xml.mapper;

import com.yifan.spring.boot.mybatis.xml.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> listCourses();

    Course getCourseById(@Param("id") int id);

    Course getCourseByColumn(@Param("column") String column, @Param("value") String value);

    Course getCourse(@Param("course") Course course);

    // insert, update, delete方法的返回值int表示被修改的行数。若插入成功，则int应该等于1
    int insertCourse(@Param("course") Course course);

    int updateCourse(@Param("course") Course course);

    int deleteCourseById(@Param("id") Integer id);

}
