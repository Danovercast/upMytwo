package com.dabai.mytwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * SpringBootApplication 上使用@ServletComponentScan 注解后
 * Servlet可以直接通过@WebServlet注解自动注册
 * Filter可以直接通过@WebFilter注解自动注册
 * Listener可以直接通过@WebListener 注解自动注册
 * <p>@author     ：dbo </p>
 * <p>@date       ：Created in 2020/7/22 17:56</p>
 * <p>@description：web端启动application</p>
 * <p>@version:     1.0</p>
 */
@SpringBootApplication
@ServletComponentScan
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
