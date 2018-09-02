package com.xry.repo.mongo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "book")
public class BookDO extends BaseDO{

    private String name;

    private String author;

    private Double price;
}
