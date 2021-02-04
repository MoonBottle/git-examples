package com.bottle.examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.var;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306580911915042
 * 线程1可以调用addTask()不断往队列中添加任务；
 * 线程2可以调用getTask()从队列中获取任务。如果队列为空，则getTask()应该等待，直到队列中至少有一个任务时再返回。
 * 因此，多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务。
 * <p>
 * synchronized this 锁
 */
public class ReadWriterLockTest {

    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();
        var addThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.inc();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
        });
        addThread.start();

        var t = new Thread() {
            public void run() {
                int[] s = counter.get();
                // 不加读锁会出现不一致的情况
                while (s[0] != s[1]) {
                    System.out.println(Arrays.toString(s));
                    s = counter.get();
                }
            }
        };
        t.start();

    }

    @Data
    @AllArgsConstructor
    static class Counter {
        private final int[] counts = new int[2];
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock readLock = lock.readLock();
        private final Lock writeLock = lock.writeLock();

        public void inc() {
            readLock.lock();
            try {
                counts[0] += 1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                counts[1] += 1;
            } finally {
                readLock.unlock();
            }
        }

        public int[] get() {
            writeLock.lock();
            try {
                return Arrays.copyOf(counts, counts.length);
            } finally {
                writeLock.unlock();
            }
        }

    }
}
