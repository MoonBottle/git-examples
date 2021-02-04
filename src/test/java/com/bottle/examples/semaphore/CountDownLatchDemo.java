package com.bottle.examples.semaphore;

import java.util.concurrent.CountDownLatch;

/**
 * https://www.cnblogs.com/leesf456/p/5406191.html
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyThread t1 = new MyThread("t1", countDownLatch);
        MyThread t2 = new MyThread("t2", countDownLatch);
        t1.start();
//        t2.start();
        System.out.println("Waiting for t1 thread and t2 thread to finish");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }

    static class MyThread extends Thread {
        private CountDownLatch countDownLatch;

        public MyThread(String name, CountDownLatch countDownLatch) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " doing something");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
            countDownLatch.countDown();
        }
    }
}
