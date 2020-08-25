package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

/**
 * Application
 *
 * @author dolyw.com
 * @date 2018/11/16 19:29
 */
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan("com.example.dao")
@PropertySource(value = {"classpath:config/generator.properties"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
