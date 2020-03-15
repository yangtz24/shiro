package com.learn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author MyConfig
 * @description
 * @date 2019/8/6 15:52
 **/
@Configuration
public class MyConfig {

    @LoadBalanced
    @Bean
    public RestTemplate getrestTemplate() {
        return new RestTemplate();
    }
}
