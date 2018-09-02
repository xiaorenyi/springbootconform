package com.xry.web.mongo.muliconfig.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

//    private MongoProperties mmProperties = new MongoProperties();
//    private MongoProperties ggProperties = new MongoProperties();

    private MMProperties mmProperties;

    private GGProperties ggProperties;

}
