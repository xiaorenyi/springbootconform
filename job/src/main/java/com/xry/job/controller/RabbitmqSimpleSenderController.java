package com.xry.job.controller;

import com.xry.job.rabbitmq.MsgBean;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-09-05 18:43
 */
@RestController
public class RabbitmqSimpleSenderController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/rabbit/simple/hello/{msg}")
    public String send(@PathVariable String msg) {
        System.out.println("hello : " + msg);
        for (int i = 0; i <= 20;i++){
            MsgBean msgBean = new MsgBean();
            msgBean.setId(i+"");
            msgBean.setMsg(msg);
            this.rabbitTemplate.convertAndSend("hello", msgBean);
        }
        return msg;
    }
}
