package com.xry.web.mongo.muliconfig.property;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BaseMongoProperties {

    private String database;

    private String username;

    private String password;

    private String host;

    private Integer port;
}
