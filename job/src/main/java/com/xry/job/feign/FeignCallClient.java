package com.xry.job.feign;

import com.xry.job.mo.ResponseMO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yuqinggen
 * @create 2018-09-11 18:47
 */
@FeignClient("conform-web")
@Component
public interface FeignCallClient {

    @GetMapping("/pullData")
    ResponseMO pullData();
}
