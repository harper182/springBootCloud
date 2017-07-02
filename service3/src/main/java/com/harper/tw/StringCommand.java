package com.harper.tw;


import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class StringCommand extends HystrixCommand<String>{

    private RestTemplate restTemplate;
    private String string;
    public StringCommand(Setter setter, RestTemplate restTemplate,String string) {
        super(setter);
        this.string = string;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://SERVICE1/hello", String.class);
    }

    @Override
    protected String getFallback() {
        return String.format("hello %s",new Random().nextInt(1000));
    }

    @Override
    protected String getCacheKey() {
        return string;
    }

//    public static void flushCache(int id) {
//        //这里从HystrixRequestCache的getInstance静态方法中找到对应实例，并将响应值清除
//        HystrixRequestCache.getInstance(GETTER_KEY,
//                HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
//    }
}
