package com.dabai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>@author     ：dbo </p>
 * <p>@date       ：Created in 2020/7/22 14:31</p>
 * <p>@description：接口提供者</p>
 * <p>@version:     1.0</p>
 */
@SpringBootApplication
@MapperScan("com.dabai.dao.*")
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }
}
