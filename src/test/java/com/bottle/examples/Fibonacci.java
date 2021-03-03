package com.bottle.examples;

public class Fibonacci {
    public static long fibonacci(long index) {
        if (index <= 1) {
            return index;
        } else {
            return fibonacci(index - 1) + fibonacci(index - 2);
        }
    }

    public static long fibonacciTailRecursion(long index) {
        return fibonacciTailRecursion(index, 0, 1);
    }

    public static long fibonacciTailRecursion(long index, int curr, int next) {
        if (index == 0) {
            return curr;
        } else {
            return fibonacciTailRecursion(index - 1, next, curr + next);
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fibonacciTailRecursion(10));
    }
}
