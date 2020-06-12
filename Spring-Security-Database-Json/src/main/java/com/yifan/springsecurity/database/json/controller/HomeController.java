package com.yifan.springsecurity.database.json.controller;

import com.yifan.springsecurity.database.json.model.APIResponse;
import com.yifan.springsecurity.database.json.model.User;
import com.yifan.springsecurity.database.json.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping({"/", "/home"})
    public APIResponse home(Model model){
        return new APIResponse(1, "Welcome to the home page. You can see this page with anonymous role");
    }

    @RequestMapping("/hello")
    public APIResponse hello(@AuthenticationPrincipal Principal principal, Model model) {
        return new APIResponse(2, "This is the hello page. You should have been logged in to see it.");
    }

    @GetMapping("/login")
    public APIResponse login() {
        return new APIResponse(3, "Please make a POST request to /doLogin with JSON");
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

    @GetMapping("/admin-only")
    @Secured("ROLE_ADMIN")
    // 也可以使用@PreAuthorize("hasRole('ROLE_ADMIN')")
    public APIResponse adminOnly() {
        return new APIResponse(4, "This page is admin-only.");
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