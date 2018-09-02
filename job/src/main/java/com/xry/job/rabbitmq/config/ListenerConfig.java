package com.xry.job.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqinggen
 * @create 2018-09-02 17:48
 */
@Configuration
public class ListenerConfig {

    private static final String QUEUE_NAME = "hello_queue";

    /**
     * 相同业务，一个topic够了，再根据不同的queue区分不同的消费者
     */
    private static final String TOPIC_EXCHANGE = "topic_exchange";

    @Bean(name = QUEUE_NAME)
    public Queue messageQueue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

//    @Bean
//    public Binding binding(@Qualifier(QUEUE_NAME) Queue queue,TopicExchange topicExchange){
//        BindingBuilder.bind()
//    }

}
