package com.tw.harper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/service0")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam("str") String str){
        return "hello "+str;
    }

    @GetMapping("hello1")
    public @ResponseBody String greeting() {
        return "hello world";
    }

    @GetMapping("hello2")
    public @ResponseBody String hello2(@RequestParam("str") String str) {
        return helloService.strToUpcase(str);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id,@RequestParam("name") String name){
        return new User(id,name,id);
    }

}
