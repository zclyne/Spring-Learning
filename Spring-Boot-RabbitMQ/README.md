# Spring Boot整合RabbitMQ Demo

### 项目介绍

这个Demo展示的是Spring Boot使用Spring AMQP整合RabbitMQ。

### 项目框架

- Spring Boot
- Spring AMQP

### 如何使用

1. 在application.properties中配置RabbitMQ连接

2. 启动项目，使用Postman等工具发送GET请求到http://localhost:8080/send，带上请求参数msg

### 代码实现步骤

1. 在Spring Initializr上生成Project，在dependencies中添加Spring Web和Spring Rabbit

2. 创建RabbitMQ的配置类```RabbitmqConfig```，在其中添加上```Queue```、```Exchange```、```Binding```的Bean

3. 在```RabbitmqConfig```中添加```Jackson2JsonMessageConverter```作为默认的序列化和反序列化器，并添加```RabbitTemplate```的Bean，设置template的```ConnectionFactory```和```MessageConverter```

4. 创建```MyMessage```类，作为所发送的消息类

5. 创建```MySender```类，实现```RabbitTemplate.ConfirmCallback```接口以实现发送消息后的回调功能，并添加```send```方法用于发送消息。在```MySender```的构造器方法中设置```RabbitTemplate```的回调为```MySender```

6. 创建```Receiver```类，用```@RabbitListener```标注```receiveMessage```方法，表示该方法用于接收消息。参数设置成MyMessage，Jackson会在接收消息时自动执行序列化

7. 创建```HomeController```类，用于接收API请求，并发送消息