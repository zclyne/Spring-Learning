# Spring-Boot-WebSocket

### 项目介绍

这个Demo展示了Spring Boot基于STOMP协议实现的WebSocket发布和订阅。前端向/app/sendMessage接口发送消息，Spring Boot在接收到该消息后，向/topic/public广播这条消息。所有订阅了这个message broker的客户端都能够接收到这条消息。

### 项目框架

- Spring Boot
- Spring Boot WebSocket

### 如何使用

直接启动项目，使用浏览器访问http://localhost:8080/index.html，使用前端进行测试

### 代码实现步骤

1. 编写```WebSocketConfig```类并继承```WebSocketMessageBrokerConfigurer```接口，覆盖```registerStompEndpoints```和```configureMessageBroker```方法，来配置WebSocket和Message Broker的API

2. 编写```WebSocketMessage```类，作为WebSocket通信时使用的消息类

3. 编写```WebSocketController```类，类似于普通的Spring MVC的```Controller```，使用```@MessageMapping```来指定接收的路径，使用```@SendTo```来指定广播的Message Broker路径