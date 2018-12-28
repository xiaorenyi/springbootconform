package com.xry.permission.controller;

import com.xry.common.trace.TraceIdContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-12-28 13:47
 */
@RestController
@Slf4j
public class TraceIdTestController {

    @GetMapping("/traceId")
    public String traceIdLog(){
        TraceIdContext.attachTraceId();
        log.info("current thread name = {}",Thread.currentThread().getName());
        log.info("current thread id = {}",Thread.currentThread().getId());
        log.info("trace id = {}",TraceIdContext.getCurrentTraceId());
        return TraceIdContext.getCurrentTraceId();
    }
}
