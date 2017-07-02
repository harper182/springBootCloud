package com.harper.tw;

import com.harper.tw.constants.Constants;
import com.harper.tw.entity.User;
import com.harper.tw.service.UserService;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    public String helloFallBack() {
        return "error111";
    }

    public String helloFallBackWithException(Throwable e) {
        System.out.println(e.getMessage());
        return "error111-1";
    }

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloFallBack",ignoreExceptions = IOException.class)
    public String hello() {
        return this.restTemplate.getForObject(String.format("http://%s/hello", Constants.SERVICE1_NAME), String.class);
    }

    @RequestMapping("/helloString")
    @HystrixCommand(fallbackMethod = "helloFallBackWithException")
    public String helloString() {
        return new StringCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(Constants.GROUP_KEY)).andCommandKey(HystrixCommandKey.Factory.asKey(Constants.COMMAND_KEY)), restTemplate, "whb").execute();
    }
    @RequestMapping("/helloString1")
    public String helloString1() throws ExecutionException, InterruptedException {
        Future<String> future = new StringCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(Constants.GROUP_KEY)).andCommandKey(HystrixCommandKey.Factory.asKey(Constants.COMMAND_KEY)), restTemplate, "whb").queue();
        return future.get();
    }
    @RequestMapping("/users")
    public String helloUsers() throws ExecutionException, InterruptedException {
        List<String> userIds = Arrays.asList("11","22","33");
        StringBuffer result = new StringBuffer();
        ResponseEntity<User[]> userResponseEntities = userService.findAll(userIds);
        for (User user: userResponseEntities.getBody()) {
            result.append(user.toString()).append("\n");
        }
        return result.toString();
    }
}
