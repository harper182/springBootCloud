package com.harper.tw;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name = "service1",url = "${services.service1.baseUrl}")
public interface ServiceAgent {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();
}
