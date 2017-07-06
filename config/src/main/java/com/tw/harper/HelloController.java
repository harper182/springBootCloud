package com.tw.harper;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by hbowang on 7/5/17.
 */
@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    @ApiOperation(value = "say hello", response = String.class)
    public String hello() {
        return "config";
    }
}
