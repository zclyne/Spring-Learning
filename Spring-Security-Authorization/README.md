# Spring-Security-Authorization

### 项目介绍

该项目展示了使用Spring Security进行API权限管理的使用方法。Demo中定义了2个角色"admin"和"user"，admin的权限比user更高，能够访问所有user能够访问的API。

### 项目框架

- Spring Boot
- Spring Security

### 如何使用

1. 使用Intellij IDEA导入项目

2. 启动AuthenticationApplication，访问相应API

### 代码实现步骤

1. 创建Spring Boot + Spring Security项目

2. 编写自定义的配置类并继承```WebSecurityConfigurerAdapter```，配置登录登出的url以及相应的handler，并设置相应的```PasswordEncoder```和内存中的User。最后还要添加角色继承关系。此处```ROLE_admin```继承```ROLE_user```。

3. 编写相应的Controller来接收HTTP请求