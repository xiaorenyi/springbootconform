package com.xry.web.controller;

import com.xry.repo.mongo.entity.CityDO;
import com.xry.repo.mongo.repo.ggdb.CityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuqinggen
 * @create 2018-08-25 16:16
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    private CityRepo cityRepo;

    @GetMapping("/hello/{msg}")
    public String sayHello(@PathVariable String msg) {
        log.info("welcome = {}", msg);
        List<CityDO> cityDOList = cityRepo.findAll();
        return "welcome " + msg + cityDOList.toString();
    }
}
