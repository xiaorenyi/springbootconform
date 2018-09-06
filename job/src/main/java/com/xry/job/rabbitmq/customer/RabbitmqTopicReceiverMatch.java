package com.xry.job.rabbitmq.customer;

import com.xry.job.rabbitmq.MsgBean;
import com.xry.job.rabbitmq.config.TopicRabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuqinggen
 * @create 2018-09-02 16:29
 */
@Component
@RabbitListener(queues = TopicRabbitmqConfig.QUEUE_MATCH)
public class RabbitmqTopicReceiverMatch {

    @RabbitHandler
    public void process(MsgBean msg) {
        System.out.println("Receiver match : " + msg);
    }
}
