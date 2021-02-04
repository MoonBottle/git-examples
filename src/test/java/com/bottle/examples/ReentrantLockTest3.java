package com.bottle.examples;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest3 {

    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        final Thread thread1 = new Thread(new ThreadDemo(lock1, lock2), "线程A");
        final Thread thread2 = new Thread(new ThreadDemo(lock2, lock1), "线程B");
        thread1.start();
        thread2.start();

//        thread1.interrupt();
    }

    @Data
    @AllArgsConstructor
    static class ThreadDemo implements Runnable {

        ReentrantLock firstLock;
        ReentrantLock secondLock;

        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly();
                TimeUnit.MICROSECONDS.sleep(50);
                secondLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + "获取到了资源，正常结束");
            }
        }
    }
}
