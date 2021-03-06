# 基于Mybatis + MySQL使用Spring Security进行JSON格式用户登录验证

### 项目介绍

这个Demo展示的是基础的使用Spring Security框架的用户名密码认证。用户名和密码来自于MySQL数据库，所有API接收的请求以及返回值都是JSON。

该Demo中的/admin-only API展示了基本的使用注解方式实现方法授权。只有拥有ROLE_ADMIN权限的用户才能够访问这个API。更复杂的API访问需要自定义Filter来实现。

### 项目框架

- Spring Boot
- Spring Security
- MyBatis
- MySQL

### 如何使用

1. 在application.properties中配置MySQL数据库连接，包括数据库名称、用户名和密码

2. 在所连接的MySQL上执行db文件夹中的create_table.sql，创建相应的数据库和表。预插入的2个用户的密码是已经经过Bcrypt加密的，原始密码为'root'。

3. 启动项目，使用Postman等工具发送JSON格式数据到http://localhost:8080下的相应API

### 代码实现步骤

1. 在Spring Boot + Spring Security项目的基础上，在pom.xml中添加对于```mysql-connector-java```和```mybatis-spring-boot-starter```的依赖

2. 在```application.properties```中配置Spring的数据源

3. 编写自定义的配置类并继承```WebSecurityConfigurerAdapter```，覆盖两个```configure```方法，在以```HttpSecurity```为参数的方法中配置哪些路径不需要认证即可访问，此处为"/"和"/home"，而其他路径都需要登录后才能访问。在以```AuthenticationManagerBuilder```为参数的方法中添加自定义的```MyUserDetailsService```，这相当于在认证链中添加了一个```DaoAuthenticationProvider```，告诉Spring Security使用这个Service到数据库中查询是否存在这个用户

4. 编写相应的Controller来接收HTTP请求

5. 创建```User```类，该类实现了```UserDetails```接口，因此能够作为MyUserDetailsService的返回值

6. 创建```UserMapper```接口，这是MyBatis所需要的用来访问数据库的接口，使用注解的方式直接把查询语句写在方法上

7. 创建```MyUserDetailsService```类，该类实现了```UserDetailsService```接口，Spring Security会自动调用该类中的```loadUserByUsername```方法来从数据库中查找用户

8. 编写自定义的Filter```UsernamePasswordJsonFilter```，继承Spring Security的```UsernamePasswordAuthenticationFilter```类，并覆盖```attemptAuthentication```方法，通过解析JSON的方式来获取用户的用户名和密码。

9. 在```WebSecurityDConfig```类中，配置自定义的过滤器以及```AuthenticationEntryPoint```，使得Spring Security不把未登录的用户重定向到新的URL，而是直接返回一个JSON告诉用户需要登录