package com.xry.common.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * @author yuqinggen
 * @create 2018-08-25 21:13
 */
public class IOUtils {

    public static void close(Closeable... closeables) {
        try {
            for (Closeable closeable : closeables) {//按照可变参数顺序，依次关闭
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void flush(Flushable... flushables) {
        try {
            for (Flushable flushable : flushables) {//按照可变参数顺序，依次刷新缓冲
                if (flushable != null) {
                    flushable.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void flush(Flushable flushable) {
        try {
            if (flushable != null) {
                flushable.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
