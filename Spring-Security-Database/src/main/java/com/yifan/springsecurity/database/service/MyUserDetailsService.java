package com.yifan.springsecurity.database.service;

import com.yifan.springsecurity.database.model.User;
import com.yifan.springsecurity.database.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @MyUserDetailsService: 实现了UserDetailsService接口，是Spring Security提供的从数据库中获取User的类
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @loadUserByUsername: 根据用户名返回相应的user
     * @param s: 用户的用户名
     * @return: UserDetails接口。此处的实现类是User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User result = userMapper.selectUserByUsername(s);
        return result;
    }

    /**
     * 向数据库中插入一个新的user
     * @param user：要插入的user
     * @return 返回值为1表示插入成功，0表示失败
     */
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public String loadRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

}
