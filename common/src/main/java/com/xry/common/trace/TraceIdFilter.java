package com.xry.common.trace;

import com.xry.common.util.UUIDShort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 每次请求都会经过该filter
 * @author born on 2017/7/18.
 */
public class TraceIdFilter extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(TraceIdFilter.class);

    public final static String HEADER_FOR_TRACE_ID = "X-TRACE-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        try {
            String traceId = request.getHeader(HEADER_FOR_TRACE_ID);

            if (StringUtils.isEmpty(traceId)) {
                traceId = UUIDShort.generate();
            }
            TraceThreadContext.setTraceId(traceId);
            response.addHeader(HEADER_FOR_TRACE_ID, traceId);

            MDC.put(TraceThreadContext.MDC_TRACE_ID, traceId);
            if (logger.isDebugEnabled()) {
                logger.debug("The request starts, requestUri:{}", request.getRequestURI());
            }

            chain.doFilter(request, response);
        } finally {
            TraceThreadContext.removeTraceId();
            long end = System.currentTimeMillis();
            if (logger.isDebugEnabled()) {
                logger.debug("The request ends. Time consuming:{}ms", end - start);
            }
            MDC.remove(TraceThreadContext.MDC_TRACE_ID);

        }
    }
}
