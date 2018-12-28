package com.xry.common.trace;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 TraceId
 * @author Beldon.
 * @create 2017-11-3 下午2:26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TraceIdConfiguration.class)
public @interface EnableTraceIdConfigured {
}
