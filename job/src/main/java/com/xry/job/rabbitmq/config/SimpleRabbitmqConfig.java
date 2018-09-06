package com.xry.job.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqinggen
 * @create 2018-09-05 18:25
 */
@Configuration
public class SimpleRabbitmqConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

}
