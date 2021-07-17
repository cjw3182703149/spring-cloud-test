package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@MapperScan(value = "com.example.dao")
@EnableEurekaClient // 配置eureka注解扫描
@EnableDiscoveryClient  // 服务发现 团队合作使用，可以不写
@EnableCircuitBreaker // 开启熔断
public class Provider8002 {
    public static void main(String[] args) {
        SpringApplication.run(Provider8002.class, args);
    }
}
