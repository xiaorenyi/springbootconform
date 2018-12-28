package com.xry.common.trace;

import com.xry.common.util.UUIDShort;
import org.slf4j.MDC;

/**
 * @author Beldon
 * @create 2018-09-07 10:11
 */
public class TraceIdContext {

    private static final ThreadLocal<String> TRACE_ID_CACHE = new ThreadLocal<>();

    private TraceIdContext() {

    }

    private static final String MDC_TRACE_ID = "traceId";

    public static void attachTraceId() {
        String traceId = UUIDShort.generate();
        attachTraceId(traceId);
    }

    public static void attachTraceId(String traceId) {
        MDC.put(MDC_TRACE_ID, traceId);
        TRACE_ID_CACHE.set(traceId);
    }


    public static void clearTraceId() {
        TRACE_ID_CACHE.remove();
    }

    public static String getCurrentTraceId() {
        return TRACE_ID_CACHE.get();
    }
}
