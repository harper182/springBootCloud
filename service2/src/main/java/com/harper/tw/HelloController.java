package com.harper.tw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RestController
public class HelloController {
    @Autowired
    private ServiceAgent serviceAgent;
    @RequestMapping("/hello")
    public String hello(){
        return serviceAgent.hello();
    }
}
