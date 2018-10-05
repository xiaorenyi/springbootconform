package com.xry.job.rabbitmq.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuqinggen
 * @create 2018-09-05 19:02
 */
@Data
public class MsgBean implements Serializable {

    private String msg;

    private String id;
}
