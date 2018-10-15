package com.xry.repo.mongo.repo.mmdb;


import com.xry.repo.mongo.entity.BookDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<BookDO, String> {
}
