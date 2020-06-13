package com.yifan.springsecurity.authorization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // 任何人都能够访问/hello API
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 仅具有admin权限的用户可以访问/admin/hello API
    @GetMapping("/admin/hello")
    public String admin() {
        return "admin";
    }

    // 具有user权限的用户可以访问/user/hello API
    @GetMapping("/user/hello")
    public String user() {
        return "user";
    }

}
