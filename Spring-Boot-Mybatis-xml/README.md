# Spring Boot使用XML方式集成MyBatis

### 项目介绍

这个Demo展示的是基础的使用Spring Boot框架集成MyBatis的Demo。此处MyBatis采用XML方式。

```db/create_table.sql```中创建了两个表，为1对多关系，1个老师可以教授多门课程，而1门课程只由1个老师来教授。


Demo中包含了基本的增删改查，展示了两表有关联关系时的SQL写法，以及MyBatis动态SQL的支持

### 项目框架

- Spring Boot
- MyBatis
- MySQL

### 如何使用

1. 在```application.properties```中配置MySQL数据库连接，包括数据库名称、用户名和密码

2. 在所连接的MySQL上执行```db```文件夹中的```create_table.sql```，创建相应的数据库和表

3. 启动项目，使用Postman等工具请求API接口，查看结果

### 代码实现步骤

1. 通过Spring Initializr构建项目，包含依赖```spring-boot-starter-web```，```mybatis-spring-boot-starter```，```mysql-connector-java```
2. 在```application.properties```中配置Spring的数据源，并配置MyBatis的xml文件路径
3. 编写MyBatis的mapper类，放在包```mapper```下
4. 编写MyBatis的mapper类相应的mapper xml文件，放在```resources/mapper```下
5. 编写Service层，该层调用Mapper层实现操作数据库的功能
6. 编写相应的Controller，接收HTTP请求，并以JSON方式返回数据

### 总结

基于XML的MyBatis配置比较繁琐，但是更加灵活，能够比较简单地实现多表联查，这是基于注解的方式所不具有的优点。