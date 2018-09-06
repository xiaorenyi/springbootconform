package com.xry.job.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqinggen
 * @create 2018-09-05 19:10
 */
@Configuration
public class TopicRabbitmqConfig {

    public final static String QUEUE_ONE = "queue-one";
    public final static String QUEUE_TWO = "queue-two";
    public final static String QUEUE_MATCH = "queue-match";

    /**
     * routing-key匹配不了，只支持. 即routing.key
     */
    public final static String ROUTING_KEY_ONE = "routing.key.one";
    public final static String ROUTING_KEY_TWO = "routing.key.two";
    public final static String ROUTING_KEY_MATCH = "routing.key.#";


    public final static String TOPIC_EXCHANGE = "topic-exchange";

    @Bean(name = TopicRabbitmqConfig.QUEUE_ONE)
    public Queue queueOne() {
        return new Queue(TopicRabbitmqConfig.QUEUE_ONE);
    }

    @Bean(name = TopicRabbitmqConfig.QUEUE_TWO)
    public Queue queueTwo() {
        return new Queue(TopicRabbitmqConfig.QUEUE_TWO);
    }

    @Bean
    public Queue queueMatch() {
        return new Queue(TopicRabbitmqConfig.QUEUE_MATCH);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeOne(@Qualifier(TopicRabbitmqConfig.QUEUE_ONE) Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(TopicRabbitmqConfig.ROUTING_KEY_ONE);
    }

    @Bean
    Binding bindingExchangeTwo(@Qualifier(TopicRabbitmqConfig.QUEUE_TWO) Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(TopicRabbitmqConfig.ROUTING_KEY_TWO);
    }

    /**
     * queueMatch 根据方法名queueMatch那对应的bean
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMatch, TopicExchange exchange) {
        return BindingBuilder.bind(queueMatch).to(exchange).with(TopicRabbitmqConfig.ROUTING_KEY_MATCH);
    }

}
