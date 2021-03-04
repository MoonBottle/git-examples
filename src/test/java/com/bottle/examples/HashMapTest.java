package com.bottle.examples;

import org.junit.Test;

public class HashMapTest {

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void test1() {
        System.out.println(tableSizeFor(0));
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(5));
        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(25));
        System.out.println(tableSizeFor(125));
        System.out.println(tableSizeFor(625));
    }

    @Test
    public void test2() {
        System.out.println(11 >>> 2);
    }

    /**
     * 根据容量参数，返回一个2的n次幂的table长度。
     */
    private static final int tableSizeFor(int c) {
        System.out.print("原始值: " + c + "--->");
        int n = c - 1;
//        n = c;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


}
