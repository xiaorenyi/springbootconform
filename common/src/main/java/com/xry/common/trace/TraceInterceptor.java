package com.xry.common.trace;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 项目中通过feign调用，会添加traceId到请求头（如果有的话）
 * @author born on 2017/7/18.
 */
public class TraceInterceptor implements RequestInterceptor {
    private static Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = TraceThreadContext.getTraceId();
        requestTemplate.header(TraceIdFilter.HEADER_FOR_TRACE_ID, traceId);
        logger.trace("traceId: {}", traceId);

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Enumeration<String> headerEnums = request.getHeaderNames();
        while (headerEnums.hasMoreElements()) {
            String headerKey = headerEnums.nextElement();
            requestTemplate.header(headerKey, request.getHeader(headerKey));
            logger.trace("header: {}-->{}", headerKey, request.getHeader(headerKey));
        }
    }
}
