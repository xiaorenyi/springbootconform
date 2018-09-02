package com.xry.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqinggen
 * @create 2018-08-26 8:37
 */
@Slf4j
@Configuration
public class ConfigurationTest {

    public ConfigurationTest(){
        log.info("spring 启动了。。。");
        log.info("spring 启动了。。。");
    }

    @Bean
    public BeanTest beanTest(){
        log.info("ConfigurationTest beanTest..");
        return new BeanTest();
    }
}
