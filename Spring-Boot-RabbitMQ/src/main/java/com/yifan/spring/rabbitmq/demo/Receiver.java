package com.yifan.spring.rabbitmq.demo;

import com.yifan.spring.rabbitmq.demo.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    // 使用注解方式注册RabbitMQ的MessageListener
    // 把ackMode设置为MANUAL来启用MySender的回调功能
    // 由于已经注册了Jackson2JsonMessageConverter的Bean，因此可以直接用MyMessage作为参数类型
    // Jackson会执行自动转换
    @RabbitListener(queues = RabbitmqConfig.queueName, ackMode = "MANUAL")
    public void receiveMessage(MyMessage message) {
        System.out.println("Received <" + message + ">");
    }

}