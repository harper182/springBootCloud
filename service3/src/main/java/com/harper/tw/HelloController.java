package com.harper.tw;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {
        String object = restTemplate.getForObject("http://hello-service/hello", String.class);
        return String.format("hello %s",object);
    }

    public String helloFallBack(){
        return "error";
    }
}
