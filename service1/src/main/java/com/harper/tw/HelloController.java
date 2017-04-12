package com.harper.tw;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "service1";
    }
}
