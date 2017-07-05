package com.harper.tw.service;

import com.harper.tw.config.DisableHystrixConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by hbowang on 7/5/17.
 */
@FeignClient(name = "SERVICE1",configuration = DisableHystrixConfiguration.class)
public interface HelloService {
}
