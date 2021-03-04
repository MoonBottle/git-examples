package com.bottle.examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306580911915042
 * 线程1可以调用addTask()不断往队列中添加任务；
 * 线程2可以调用getTask()从队列中获取任务。如果队列为空，则getTask()应该等待，直到队列中至少有一个任务时再返回。
 * 因此，多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务。
 * <p>
 * synchronized this 锁
 */
public class WaitNotifyTest {

    public static void main(String[] args) throws InterruptedException {
        var q = new TaskQueue();
        var ts = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            var t = new Thread() {
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String s = q.getTask();
                            System.out.println(Thread.currentThread().getName() + "-execute task: " + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }
        var addThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println(Thread.currentThread().getName() + "-add task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });
        addThread.start();
        addThread.join();
        Thread.sleep(100);
        for (var t : ts) {
            t.interrupt();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TaskQueue {
        Queue<String> queue = new LinkedList<>();

        public synchronized void addTask(String s) {
            this.queue.add(s);
            this.notifyAll();
        }

        /**
         * synchronized this 锁
         */
        public synchronized String getTask() throws InterruptedException {
            while (queue.isEmpty()) {
                // wait()方法必须在当前获取的锁对象上调用，这里获取的是this锁，因此调用this.wait()。
                // 必须在synchronized块中才能调用wait()方法
                // 因为wait()方法调用时，会释放线程获得的锁，wait()方法返回后，线程又会重新试图获得锁。
                // 释放 this 锁，当前线程进入等待状态，等待被其他线程唤醒后wait才会返回，白话，就是当前线程卡住了
                this.wait();
                // 重新获取 this 锁
            }
            return queue.remove();
        }

    }
}
