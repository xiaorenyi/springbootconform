package com.xry.repo.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BaseDO {

    @Id
    private String _id;

    private Long createTime;

    private Long updateTime;
}
