package com.harper.tw;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${services.service1.baseUrl}", name = "${services.service1.name}")
public interface ServiceAgent {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();
}
