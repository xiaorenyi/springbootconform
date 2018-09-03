package com.xry.web.controller;

import com.xry.web.mongo.muliconfig.GGMongoConfig;
import com.xry.web.mongo.muliconfig.MMMongoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

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
