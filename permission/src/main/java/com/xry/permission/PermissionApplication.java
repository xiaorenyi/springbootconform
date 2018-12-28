package com.xry.permission;

import com.xry.common.trace.EnableTraceIdConfigured;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**EnableTraceIdConfigured 注解可以启动traceId跟踪
 * 生效原理：@Import注解可以把bean交给spring管理
 * @author yuqinggen
 * @create 2018-11-05 19:22
 */
@SpringBootApplication
@EnableTraceIdConfigured
public class PermissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class,args);
    }
}
