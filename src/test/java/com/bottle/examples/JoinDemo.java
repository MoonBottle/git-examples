package com.bottle.examples;

import lombok.SneakyThrows;

/**
 * https://www.jianshu.com/p/fc51be7e5bc0
 */
public class JoinDemo implements Runnable {
    int i;
    Thread previousThread; //上一个线程

    public JoinDemo(Thread previousThread, int i) {
        this.previousThread = previousThread;
        this.i = i;
    }

    @SneakyThrows
    @Override
    public void run() {
        try {
            //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(previousThread.getName() + " previousThread state :" + previousThread.getState());
//        TimeUnit.HOURS.sleep(1);
        System.out.println("num:" + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread joinDemo = new Thread(new JoinDemo(previousThread, i), "线程" + i);
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}

