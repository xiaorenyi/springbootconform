package com.xry.job.controller;

import com.xry.job.elasticjob.properties.JobProperties;
import com.xry.job.elasticjob.properties.ZookeeperConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;

/**
 * @author yuqinggen
 * @create 2018-09-11 20:26
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private ZookeeperConfig zookeeperConfig;
    @Autowired
    private JobProperties jobProperties;

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable String msg){
        log.info("msg");
        System.out.println(zookeeperConfig);
        System.out.println(jobProperties);
        return "hello job" + msg;
    }
}
