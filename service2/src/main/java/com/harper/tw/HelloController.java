package com.harper.tw;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private ServiceAgent serviceAgent;

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello() {
        return serviceAgent.hello();
    }

    public String helloFallBack(){
        return "error";
    }
}
