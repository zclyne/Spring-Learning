package com.yifan.springsecurity.database.controller;

import com.yifan.springsecurity.database.model.User;
import com.yifan.springsecurity.database.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 主页和登录页面映射
 */
@Controller
public class HomeController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping({"/", "/home"})
    public String home(Model model){
        return "home";
    }

    @RequestMapping("/hello")
    public String hello(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        //从SecurityContextHolder中得到Authentication对象，进而获取权限列表，传到前端
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "hello";
    }

    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        // 向model中放一个空的user，用来接收前端form表单中的内容
        // 这样前端在post表单时，后端controller就可以直接解析成User对象
        model.addAttribute("user", new User());
        return "signup/signup";
    }

    @GetMapping("/signup/{page}")
    public String signUpSuccess(@PathVariable String page) {
        if ("success".equals(page)) {
            return "signup/success";
        } else {
            return "signup/error";
        }
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, HttpServletRequest request) {
        // 把authority设置为ROLE_USER
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        user.setRoles(authorities);
        // 把密码使用BCrypt加密，并重新存放回去
        String plainPassword = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(plainPassword));
        int result = myUserDetailsService.insertUser(user);
        if (result == 1) {
            // 自动登录，注意这里要传入的是BCrypt加密之前的password而不是加密后的password，否则无法通过authenticate
            authenticateUserAndSetSession(user.getUsername(), plainPassword, user.getAuthorities(), request);
            return "redirect:signup/success";
        } else {
            return "redirect:signup/error";
        }
    }

    // 设定已认证的user，并设置session
    private void authenticateUserAndSetSession(String username, String password, Collection<? extends GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        try {
            Authentication authenticatedUser = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}