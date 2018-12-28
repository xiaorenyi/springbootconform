package com.xry.common.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author born on 2017/7/18.
 */
public class TraceThreadContext {

    private static final Logger log = LoggerFactory.getLogger(TraceThreadContext.class);
    public final static String MDC_TRACE_ID = "traceId";

    public static final String TRACE_ID = TraceThreadContext.class.getName() + "_TRACE_ID_KEY";

    private static final ThreadLocal<Map<Object, Object>> resources = new InheritableThreadLocalMap<>();

    public TraceThreadContext() {
    }

    public static Map<Object, Object> getResources() {
        return resources != null ? new HashMap<Object, Object>(resources.get()) : null;
    }

    public static void setResources(Map<Object, Object> newResources) {
        if (newResources == null || newResources.isEmpty()) {
            return;
        }
        resources.get().clear();
        resources.get().putAll(newResources);
    }

    private static Object getValue(Object key) {
        return resources.get().get(key);
    }

    public static Object get(Object key) {
        if (log.isTraceEnabled()) {
            String msg = "get() - in thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        Object value = getValue(key);
        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Retrieved value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] " + "bound to thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
        return value;
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }

        resources.get().put(key, value);

        if (log.isTraceEnabled()) {
            String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] to thread " + "[" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
    }

    public static Object remove(Object key) {
        Object value = resources.get().remove(key);

        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" +
                    key + "]" + "from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }

    public static void remove() {
        resources.remove();
    }

    public static String getTraceId() {
        return (String) get(TRACE_ID);
    }

    public static void setTraceId(String xid) {
        put(TRACE_ID, xid);
    }

    public static void removeTraceId() {
        remove(TRACE_ID);
    }


    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {

        @Override
        protected Map<Object, Object> initialValue() {
            return new HashMap<Object, Object>();
        }

        @SuppressWarnings({"unchecked"})
        @Override
        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            if (parentValue != null) {
                return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }
}

