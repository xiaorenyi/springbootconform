package com.xry.repo.mongo.repo.ggdb;


import com.xry.repo.mongo.entity.CityDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepo extends MongoRepository<CityDO, String> {
}
