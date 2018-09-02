package com.xry.repo.mybatis.entity;

import lombok.Data;

@Data
public class UserDO {

    private Long id;

    private String username;

    private Integer age;

    private Double balance;
}
