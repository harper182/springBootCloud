package com.tw.harper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tw.harper")
public class Service0Application {
    public static void main(String[] args) {
        SpringApplication.run(Service0Application.class,args);
    }
}

