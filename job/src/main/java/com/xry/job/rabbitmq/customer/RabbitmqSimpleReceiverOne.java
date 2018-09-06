package com.xry.job.rabbitmq.customer;

import com.xry.job.rabbitmq.MsgBean;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuqinggen
 * @create 2018-09-05 18:47
 */
@Component
@RabbitListener(queues = "hello")
public class RabbitmqSimpleReceiverOne {

//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver  one : " + hello);
//    }

    @RabbitHandler
    public void process(MsgBean msg) {
        System.out.println("Receiver  one : " + msg);
    }
}
