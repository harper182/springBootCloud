package com.harper.tw;


import com.harper.tw.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RefreshScope
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @Value("${hello.name: default}")
    private String message;

    @GetMapping("/hello")
    @ApiOperation(value = "say service1", response = String.class)
    public String hello() throws InterruptedException {
        ServiceInstance serviceInstance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        return "service1";
    }

    @GetMapping("/hello1")
    @ApiOperation(value = "say service1111", response = String.class)
    public String hello11() throws InterruptedException {
        return "service1111";
    }

    @GetMapping("/helloString/{str}")
    @ApiOperation(value = "say service str", response = String.class)
    public String helloString(@PathVariable("str") String string) {
        ServiceInstance serviceInstance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "service1:" + string;
    }

    @GetMapping("/user")
    @ApiOperation(value = "hello user", response = User.class)
    public User helloUser() throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        return new User(String.valueOf(sleepTime), "tw");
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "hello user", response = User.class)
    public User helloUser(@PathVariable("id") String id) throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        return new User(String.valueOf(sleepTime), "tw");
    }

    @GetMapping("/users/{ids}")
    @ApiOperation(value = "hello users", response = List.class)
    public List<User> helloUsers(@PathVariable("ids") List<String> ids) throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        return ids.stream().map(id -> new User(id, "tw" + id)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "hello "+name;
    }
    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public User hello3(@RequestHeader String name,@RequestHeader String id ){
        return new User(id,name);
    }
    @RequestMapping(value = "/hello4", method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "Hello "+user.getName() +","+user.getId();
    }

    @GetMapping("/hello-config")
    @ApiOperation(value = "say hello", response = String.class)
    public String helloConfig()  {
        return message;
    }
}