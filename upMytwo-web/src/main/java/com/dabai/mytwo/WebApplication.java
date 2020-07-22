package com.dabai.mytwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>@author     ：dbo </p>
 * <p>@date       ：Created in 2020/7/22 17:56</p>
 * <p>@description：web端启动application</p>
 * <p>@version:     1.0</p>
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
