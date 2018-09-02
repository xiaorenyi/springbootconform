package com.xry.web.mongo.muliconfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.xry.repo.mongo.repo.ggdb",
        mongoTemplateRef = GGMongoConfig.MONGO_TEMPLATE)
public class GGMongoConfig {
    protected static final String MONGO_TEMPLATE = "ggMongoTemplate";
}
