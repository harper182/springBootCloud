package com.harper.tw;

import com.harper.tw.entity.User;
import com.harper.tw.service.RabbitService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    private ServiceAgent serviceAgent;

    @Autowired
    private RabbitService rabbitService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello() {
        return serviceAgent.hello();
    }

    public String helloFallBack() {
        return "error";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello22(@RequestParam String name) {
        return serviceAgent.hello2(name);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public User hello33(@RequestHeader("name") String name, @RequestHeader("id") String id) {
        return serviceAgent.hello3(name, id);
    }

    @RequestMapping(value = "/hello4", method = RequestMethod.POST)
    public String hello44(@RequestBody User user) {
        return serviceAgent.hello4(user);
    }

    @RequestMapping(value = "/helloRabbit", method = RequestMethod.GET)
    public String helloRabbit() {
        return rabbitService.sendMsg();
    }


}
