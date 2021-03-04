package com.bottle.examples;

import java.time.LocalTime;

public class MultiThreadTest {

    public static void main(String[] args) {
        // 继承 Thread 类
//        testMyThread(new MyThread());

        // 实现 Runnable 接口
        testMyThread(new Thread(new MyThread2()));

        // 守护线程
//        testTimerThread();
    }

    private static void testMyThread(Thread t) {
        t.start();    //启动子线程
        //主线程继续同时向下执行
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + " ");
        }
    }

    private static void testTimerThread() {
        Thread t = new Thread(new TimerThread());
        // 守护线程，不影响 JVM 退出
        t.setDaemon(true);
        t.start();    //启动子线程
    }

    public static class MyThread extends Thread {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(this.getName() + "-" + i + " ");
            }
        }
    }

    public static class MyThread2 implements Runnable {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i + " ");
            }
        }
    }

    static class TimerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
