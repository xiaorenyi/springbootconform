package com.xry.web.controller;

import com.xry.web.mo.RequestMO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-09-12 20:23
 */
@Api(tags = "swagger2接口")
@RestController
public class Swagger2Controller {

    @GetMapping("/swagger")
    @ApiOperation("swagger2 test")
    public String swagger(@RequestBody RequestMO requestMO){
        return "swagger 文档";
    }
}
