package com.tw.harper;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String strToUpcase(String str){
        return str.toUpperCase();
    }
}
