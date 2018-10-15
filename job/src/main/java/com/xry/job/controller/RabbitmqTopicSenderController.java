package com.xry.job.controller;

import com.xry.job.rabbitmq.MsgBean;
import com.xry.job.rabbitmq.config.TopicRabbitmqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-09-02 16:25
 */
@RestController
public class RabbitmqTopicSenderController {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/rabbit/topic/one/{msg}")
    public String topicSenderOne(@PathVariable String msg) {

        MsgBean msgBean = new MsgBean();
        msgBean.setMsg(msg);
        msgBean.setId("123456");

        this.rabbitTemplate.convertAndSend(TopicRabbitmqConfig.TOPIC_EXCHANGE, TopicRabbitmqConfig.ROUTING_KEY_ONE, msgBean);

        return msg;
    }

    @GetMapping("/rabbit/topic/two/{msg}")
    public String topicSenderTwo(@PathVariable String msg) {

        MsgBean msgBean = new MsgBean();
        msgBean.setMsg(msg);
        msgBean.setId("654321");

        this.rabbitTemplate.convertAndSend(TopicRabbitmqConfig.TOPIC_EXCHANGE, TopicRabbitmqConfig.ROUTING_KEY_TWO, msgBean);

        return msg;
    }

    @GetMapping("/rabbit/topic/match/{msg}")
    public String topicSenderMatch(@PathVariable String msg) {

        MsgBean msgBean = new MsgBean();
        msgBean.setMsg(msg);
        msgBean.setId("666666");

        this.rabbitTemplate.convertAndSend(TopicRabbitmqConfig.TOPIC_EXCHANGE, TopicRabbitmqConfig.ROUTING_KEY_MATCH, msgBean);

        return msg;
    }

}
