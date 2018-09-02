package com.xry.job.rabbitmq.constant;

import org.springframework.amqp.core.TopicExchange;

/**
 * 简单概念：
 * 交换机：Exchange 用于转发消息，但是它不会做存储 ，如果没有 Queue bind 到 Exchange 的话，
 *         它会直接丢弃掉 Producer 发送过来的消息。这里有一个比较重要的概念：路由键 。
 *         消息到交换机的时候，交互机会转发到对应的队列中，那么究竟转发到哪个队列，就要根据该路由键。
 * 示例程序使用topic模式，还有其他模式：Fanout、Headers
 * @author yuqinggen
 * @create 2018-09-02 17:05
 */
public class RabbitmqConstant {

    public static final TopicExchange TOPIC_EXCHANGE = new TopicExchange("com.xry.topic");

    public static final String ROUTING_KEY = "routing_key"; //路由键a

    public static final String QUEUE = "queue"; //队列a

}
