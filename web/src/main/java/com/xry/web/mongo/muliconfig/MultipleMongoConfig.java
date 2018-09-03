package com.xry.web.mongo.muliconfig;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.xry.web.controller.ConfigurationTest;
import com.xry.web.mongo.muliconfig.property.BaseMongoProperties;
import com.xry.web.mongo.muliconfig.property.MultipleMongoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class MultipleMongoConfig {


    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Primary
    @Bean(name = MMMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate mmMongoTemplate() {
        return new MongoTemplate(mmFactory(this.mongoProperties.getMmProperties()));
    }

    @Bean
    @Qualifier(GGMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate ggMongoTemplate() {
        return new MongoTemplate(ggFactory(this.mongoProperties.getGgProperties()));
    }

    @Bean
    @Primary
    public MongoDbFactory mmFactory(BaseMongoProperties mongo) {
        return builtMongoDbFactory(mongo);
    }

    @Bean
    public MongoDbFactory ggFactory(BaseMongoProperties mongo) {
        return builtMongoDbFactory(mongo);
    }

    private MongoDbFactory builtMongoDbFactory(BaseMongoProperties mongo) {
        ServerAddress address = new ServerAddress(mongo.getHost(), mongo.getPort());
        List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
        MongoCredential credential = MongoCredential.createCredential(mongo.getUsername(), mongo.getDatabase(), mongo.getPassword().toCharArray());
        credentialList.add(credential);
        MongoClient mongoClient = new MongoClient(address, credentialList);
        return new SimpleMongoDbFactory(mongoClient, mongo.getDatabase());
    }
}
