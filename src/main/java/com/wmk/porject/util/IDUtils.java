package com.wmk.porject.util;

/**
 * @author Sunth
 * @DateTime 2019-04-15 15:37
 * 描述
 */
public class IDUtils {
    private static byte[] lock = new byte[0];

    // 位数，默认是8位
    private final static long w = 1000;

    public static String createID() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }
}
