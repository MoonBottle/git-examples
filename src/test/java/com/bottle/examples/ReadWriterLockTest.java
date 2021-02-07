package com.bottle.examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.var;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class ReadWriterLockTest {

    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();

        newWriteThread(counter, "写线程1");
        newWriteThread(counter, "写线程2");
        newWriteThread(counter, "写线程3");

        newReadThread(counter, "读线程1");
        newReadThread(counter, "读线程2");

    }

    private static void newWriteThread(Counter counter, String threadName) {
        new Thread(() -> {
            while (true) {
                counter.inc();
            }

        }, threadName).start();
    }

    private static void newReadThread(Counter counter, String threadName) {
        new Thread(() -> {
            while (true) {
                int[] s = counter.get();
                System.out.println(Thread.currentThread().getName() + "-读取到数据" + Arrays.toString(s));
            }
        }, threadName).start();
    }

    @Data
    @AllArgsConstructor
    static class Counter {
        private final int[] counts = new int[2];

        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        public void inc() {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "- 获得写锁");

            try {
                counts[0] += 1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                counts[1] += 1;

                System.out.println(Thread.currentThread().getName() + "- 写入数据" + Arrays.toString(counts));
            } finally {
                writeLock.unlock();
                System.out.println(Thread.currentThread().getName() + "- 释放写锁");
            }
        }

        public int[] get() {
            // 没有读锁的情况下，会导致
            // 读到 读线程2-读取到数据[45, 44] 这种counts[0] ！= counts[1]的数据
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "- 获得读锁");

            try {
                return Arrays.copyOf(counts, counts.length);
            } finally {
                readLock.unlock();
                System.out.println(Thread.currentThread().getName() + "- 释放读锁");
            }
        }

    }
}
