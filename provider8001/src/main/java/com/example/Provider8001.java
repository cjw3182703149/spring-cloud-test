package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = "com.example.dao")
public class Provider8001 {
    public static void main(String[] args) {
        SpringApplication.run(Provider8001.class, args);
    }
}