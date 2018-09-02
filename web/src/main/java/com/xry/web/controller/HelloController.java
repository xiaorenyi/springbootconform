package com.xry.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-08-25 16:16
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello/{msg}")
    public String sayHello(@PathVariable String msg){
        log.info("welcome = {}",msg);
        return "welcome "+msg;
    }
}
