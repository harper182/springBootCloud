package com.harper.tw;


import com.harper.tw.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Random;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    @ApiOperation(value = "say service1",response = String.class)
    public String hello() throws InterruptedException {
        ServiceInstance serviceInstance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        return "service1";
    }
    @GetMapping("/hello1")
    @ApiOperation(value = "say service1111",response = String.class)
    public String hello11() throws InterruptedException {
        return "service1111";
    }
    @GetMapping("/helloString/{str}")
    @ApiOperation(value = "say service str",response = String.class)
    public String helloString (@PathVariable("str") String string ) {
        ServiceInstance serviceInstance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "service1:"+string;
    }

    @GetMapping("/user")
    @ApiOperation(value = "hello user",response = User.class)
    public User helloUser() throws InterruptedException{
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        return new User(sleepTime,"tw");
    }
    @GetMapping("/user/{id}")
    @ApiOperation(value = "hello user",response = User.class)
    public User helloUser(@PathVariable("id") String id) throws InterruptedException{
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        return new User(Integer.valueOf(id),"tw");
    }
}
