package com.harper.tw.command;

import com.harper.tw.constants.Constants;
import com.harper.tw.entity.User;
import com.harper.tw.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.Arrays;
import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<User>>{

    private UserService userService;
    private List<String> userIds;
    public UserBatchCommand(UserService userService,List<String> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(Constants.GROUP_KEY)).andCommandKey(HystrixCommandKey.Factory.asKey(Constants.COMMAND_KEY)));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {
        User[] users = userService.findAll(userIds).getBody();
        return Arrays.asList(users);
    }
}
