package com.harper.tw;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@EnableBinding(RabbitService.BindMessage.class)
public class RabbitService {

    @StreamListener(BindMessage.MSG)
    public void printMsg(){
        System.out.println("******************************************");
        System.out.println("receviced msg");
        System.out.println("******************************************");
    }

    public interface BindMessage{
        String MSG = "RABBIT_MSG";

        @Input(MSG)
        SubscribableChannel hello();
    }
}
