package com.xry.web.controller;

import com.xry.web.mo.ResponseMO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-09-11 18:54
 */
@RestController
public class FeignServiceController {


    @GetMapping("/pullData")
    public ResponseMO getData() {
        ResponseMO responseMO = new ResponseMO();
        responseMO.setMsg("pull data success");
        return responseMO;
    }
}
