package com.xry.job.controller;

import com.xry.job.feign.FeignCallClient;
import com.xry.job.mo.ResponseMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-09-11 18:45
 */
@RestController
public class FeignCallController {


    @Autowired
    private FeignCallClient feignCallClient;

    @GetMapping("/feign")
    public ResponseMO feignCall() {

        return feignCallClient.pullData();
    }
}
