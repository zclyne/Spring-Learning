package com.yifan.springsecurity.database.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yifan.springsecurity.database.json.filter.UsernamePasswordJsonFilter;
import com.yifan.springsecurity.database.json.model.APIResponse;
import com.yifan.springsecurity.database.json.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
// 需要添加下面这个注解来启用@Secured
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                // 这里必须仍然配置为formLogin(), 不然UsernamePasswordAuthenticationFilter不会出现
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                // 设置未登录时的异常响应，因为此处采用JSON格式登录，因此当未登录的用户访问需要登录的页面时，
                // 不是将其重定向到一个新的url，而是返回一个JSON，告诉用户需要登录
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
                        PrintWriter out = response.getWriter();
                        APIResponse apiResponse = new APIResponse(1234, "请求失败！");
                        if (authException instanceof InsufficientAuthenticationException) {
                            // 当未登录的用户访问一个需要登录的资源时，就会进入这条if语句
                            authException.printStackTrace();
                            apiResponse.setMessage("请先登录！");
                        }
                        out.write(new ObjectMapper().writeValueAsString(apiResponse));
                        out.flush();
                        out.close();
                    }
                });
        // 在原本的UsernamePasswordAuthenticationFilter的位置上注册自定义的Filter
        http.addFilterAt(getUsernamePasswordJsonFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // 获取自定义的filter
    @Bean
    protected UsernamePasswordJsonFilter getUsernamePasswordJsonFilter() throws Exception {
        UsernamePasswordJsonFilter filter = new UsernamePasswordJsonFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter out = response.getWriter();
                APIResponse apiResponse = new APIResponse(1, "Successfully logged in!");
                out.write(new ObjectMapper().writeValueAsString(apiResponse));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter out = response.getWriter();
                APIResponse apiResponse = new APIResponse(1, "Failed to login! Please try again");
                out.write(new ObjectMapper().writeValueAsString(apiResponse));
                out.flush();
                out.close();
            }
        });
        // 直接使用WebSecurityConfigurerAdapter提供的AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 添加自定义的UserDetailsService，相当于在认证链中添加了一个DaoAuthenticationProvider
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 添加authenticationManager bean, 这对于用户注册后的自动登录是必要的
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
