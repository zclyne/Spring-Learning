package com.yifan.springsecurity.database.mapper;

import com.yifan.springsecurity.database.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

// MyBatis的mapper文件，此处采用注解的方式查询数据库

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO user(name, address, username, password, roles) VALUES(#{name}, #{address}, #{username}, #{password}, #{roles});")
    int insertUser(User user);

    @Select("SELECT roles FROM user WHERE username = #{username}")
    String selectRolesByUsername(@Param("username") String username);

}
