package com.yifan.spring.rabbitmq.demo;

import com.yifan.spring.rabbitmq.demo.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

// 要求在消息发送后进行回调，因此需要实现RabbitTemplate.ConfirmCallback接口
@Component
public class MySender implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MySender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // 设置MySender的实例为callback
        rabbitTemplate.setConfirmCallback(this);
    }

    public void send(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        // 构造要发送的消息
        MyMessage message = new MyMessage(correlationId.getId(), content);
        rabbitTemplate.convertAndSend(RabbitmqConfig.topicExchangeName, "foo.bar.yifan", message, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("Successfully received a callback!");
        System.out.println("callback id = " + correlationData);
        System.out.println("ack = " + ack);
        System.out.println("cause = " + cause);
    }
}
