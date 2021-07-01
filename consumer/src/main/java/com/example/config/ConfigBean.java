package com.example.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    // 配置负载均衡实现RestTemplate
    // IRule是核心配置网关接口
    // AvailabilityFilteringRule:会过滤不能使用的服务，然后进行轮询
    // RetryRule： 轮询，如果服务获取失败，则在指定时间内进行重试
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
