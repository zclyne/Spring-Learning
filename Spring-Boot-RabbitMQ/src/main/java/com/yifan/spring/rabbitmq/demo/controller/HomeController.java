package com.yifan.spring.rabbitmq.demo.controller;

import com.yifan.spring.rabbitmq.demo.MySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private MySender sender;

    @GetMapping("/send")
    public void sendMessage(@RequestParam(name = "msg") String msg) {
        sender.send(msg);
        return;
    }

}
