package com.zpq.bigincident;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        // 提供一个ThreadLocal对象
        ThreadLocal<Object> t1 = new ThreadLocal<>();

        // 开启两个线程
        new Thread(() -> {
            t1.set("lion");
            System.out.println(Thread.currentThread().getName() + ": " + t1.get());
            System.out.println(Thread.currentThread().getName() + ": " + t1.get());
            System.out.println(Thread.currentThread().getName() + ": " + t1.get());
        }, "yellow").start();

        new Thread(() -> {
            t1.set("penguin");
            System.out.println(Thread.currentThread().getName() + ": " + t1.get());
            System.out.println(Thread.currentThread().getName() + ": " + t1.get());
            System.out.println(Thread.currentThread().getName() + ": " + t1.get());
        },"black").start();
    }
}
