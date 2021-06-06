package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer   // 服务端的启动类
public class SpringCloudEureka {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEureka.class, args);
    }
}
