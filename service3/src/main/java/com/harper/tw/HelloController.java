package com.harper.tw;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    public String helloFallBack() {
        return "error111";
    }

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello() {
        return this.restTemplate.getForObject(String.format("http://%s/hello",Constants.SERVICE1_NAME), String.class);
    }

    @RequestMapping("/helloString")
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloString() {
        return new StringCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(Constants.GROUP_KEY)).andCommandKey(HystrixCommandKey.Factory.asKey(Constants.COMMAND_KEY)), restTemplate, "whb").execute();
    }
    @RequestMapping("/helloString1")
    public String helloString1() throws ExecutionException, InterruptedException {
        Future<String> future = new StringCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(Constants.GROUP_KEY)).andCommandKey(HystrixCommandKey.Factory.asKey(Constants.COMMAND_KEY)), restTemplate, "whb").queue();
        return future.get();
    }
}
