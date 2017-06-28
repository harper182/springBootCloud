package com.harper.tw;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    public String helloFallBack(){
        return "error111";
    }

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello(){
        return this.restTemplate.getForObject("http://SERVICE1/hello",String.class);
    }
}
