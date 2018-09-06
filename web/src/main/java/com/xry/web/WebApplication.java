package com.xry.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(value = {"com.xry.web","com.xry.repo"})
@MapperScan(value = "com.xry.repo.mybatis.mapper")
//启用多数据源，这里就不要启用@EnableMongoRepositories注解
@EnableMongoRepositories(basePackages = "com.xry.repo.mongo")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
