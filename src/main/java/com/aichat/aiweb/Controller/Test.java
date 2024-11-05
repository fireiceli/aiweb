package com.aichat.aiweb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Test {
    @RequestMapping("/hello")
    public String test1(){
        log.info("this is logback");
        return "hello world__";
    }
    @PostMapping("/hello2")
    public String test2(@RequestParam String name){
        log.info("this is post method");
        return "hello"+name;
    }
}
