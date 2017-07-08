package com.harper.tw;


import com.harper.tw.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service1",url = "${services.service1.baseUrl}")
//@FeignClient(name = "service1",url = "${services.service1.baseUrl}",fallback = HelloServiceFallback.class)
public interface ServiceAgent {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();

    @RequestMapping(method = RequestMethod.GET, value = "/hello2")
    String hello2(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/hello3")
    User hello3(@RequestHeader("name") String name,@RequestHeader("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/hello4")
    String hello4(@RequestBody User user);
}
