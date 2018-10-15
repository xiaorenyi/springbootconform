package com.xry.repo.mongo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "city")
public class CityDO extends BaseDO {

    private String name;

    private String specialty;
}
