package com.harper.tw.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by hbowang on 7/5/17.
 * 如果不想全局关闭Hystrix支持,而只想针对某个服务客户端关闭Hystrix支持时,需要通过使用@Scope("prototype")注解为指定的客户端配置Feign.Builder实例
 */
@Configuration
public class DisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
