package com.harper.tw.service;

import com.harper.tw.constants.Constants;
import com.harper.tw.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by hbowang on 7/2/17.
 */
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public User find(Long id) {
        return restTemplate.getForObject(String.format("http://%s/user/{1}", Constants.SERVICE1_NAME),User.class,id);
    }
    public ResponseEntity<User[]> findAll(List<String> ids){
        return restTemplate.getForEntity(String.format("http://%s/users/{1}", Constants.SERVICE1_NAME), User[].class, StringUtils.join(ids, ","));
    }
}
