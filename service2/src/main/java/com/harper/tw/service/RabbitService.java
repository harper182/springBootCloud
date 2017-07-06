package com.harper.tw.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@EnableBinding(RabbitService.BindMessage.class)
public class RabbitService {

    @Autowired
    private BindMessage bindMessage;

    public String sendMsg(){
        String nowTime=new Date().toLocaleString();
        System.out.println("***********************************");
        System.out.println(nowTime);
        bindMessage.hello().send(MessageBuilder.withPayload("hello").build());
        System.out.println("***********************************");
        return nowTime;
    }

    public interface BindMessage{
        String MSG = "RABBIT_MSG";

        @Output(MSG)
        MessageChannel hello();
    }
}
