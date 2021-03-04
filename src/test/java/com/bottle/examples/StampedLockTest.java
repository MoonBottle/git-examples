package com.bottle.examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.var;

import java.util.concurrent.locks.StampedLock;

/**
 *
 */
public class StampedLockTest {

    public static void main(String[] args) throws InterruptedException {
        var point = new Point(0, 0);

        var writeThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                point.move(i, i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
        });
        writeThread.start();

        var readThread = new Thread() {
            public void run() {
                double s = point.distanceFromOrigin();
                System.out.println(s);
            }
        };
        readThread.start();

    }

    @Data
    @AllArgsConstructor
    static class Point {
        private double x;
        private double y;
        private final StampedLock stampedLock = new StampedLock();

        public void move(double deltaX, double deltaY) {
            long stamp = stampedLock.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        }

        public double distanceFromOrigin() {
            long stamp = stampedLock.tryOptimisticRead();
            double currentX = x;
            double currentY = y;

            if (!stampedLock.validate(stamp)) {
                stamp = stampedLock.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }

            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

    }
}
