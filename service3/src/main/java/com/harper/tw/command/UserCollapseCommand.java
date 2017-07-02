package com.harper.tw.command;

import com.harper.tw.constants.Constants;
import com.harper.tw.entity.User;
import com.harper.tw.service.UserService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbowang on 7/2/17.
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>,User,String> {
    private UserService userService;
    private String userId;
    public UserCollapseCommand(UserService userService,String userId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand")));
        this.userId = userId;
        this.userService = userService;
    }

    @Override
    public String getRequestArgument() {
        return userId;
    }

    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, String>> collapsedRequests) {
        List<String> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService,userIds);
    }

    @Override
    protected void mapResponseToRequests(List<User> batchResponse, Collection<CollapsedRequest<User, String>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<User,String> collapsedRequest : collapsedRequests){
            collapsedRequest.setResponse(batchResponse.get(count++));
        }
    }

}
