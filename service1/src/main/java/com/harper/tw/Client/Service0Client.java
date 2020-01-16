package com.harper.tw.Client;


import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "service0")
public interface Service0Client {
}
