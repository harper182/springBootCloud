package com.harper.tw.service;

import com.google.common.util.concurrent.Service;
import com.harper.tw.ServiceAgent;
import com.harper.tw.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hbowang on 7/5/17.
 */
public class HelloServiceFallback implements ServiceAgent{

    @Override
    public String hello() {
        return null;
    }

    @Override
    public String hello2(@RequestParam("name") String name) {
        return null;
    }

    @Override
    public User hello3(@RequestHeader("name") String name, @RequestHeader("id") String id) {
        return null;
    }

    @Override
    public String hello4(@RequestBody User user) {
        return null;
    }
}
