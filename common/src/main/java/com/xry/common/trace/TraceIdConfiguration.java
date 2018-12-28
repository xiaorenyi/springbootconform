package com.xry.common.trace;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

public class TraceIdConfiguration {
    @Bean
    public FilterRegistrationBean traceIdFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new TraceIdFilter());
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE + 300);
        filter.addUrlPatterns("/*");
        return filter;
    }


    @Configuration
    @ConditionalOnClass(name = "feign.RequestInterceptor")
    public static class InnerTraceInterceptorConfiguration {
        @Bean
        public TraceInterceptor traceInterceptor() {
            return new TraceInterceptor();
        }
    }

}

