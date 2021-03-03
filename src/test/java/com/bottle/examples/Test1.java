package com.bottle.examples;

import lombok.Data;
import org.junit.Test;

public class Test1 {

    @Test
    public void test1() {
        final T t0 = new T();
        final T t1 = new T();

        t0.setA(t1.getB());
    }

    @Data
    public static class T {
        int a;
        Integer b;
    }
}
