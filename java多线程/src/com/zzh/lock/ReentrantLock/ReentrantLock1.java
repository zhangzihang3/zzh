package com.zzh.lock.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1 {
    private static Lock lock = new ReentrantLock();

    public static void say() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "say");
            Thread.sleep(100);
            eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //可以把锁释放加在这
        // lock.unlock();
    }

    public static void eat() {
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getId() + "eat");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //可以把锁释放加在这
        lock.unlock();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    say();
                }
            }).start();
        }
    }
}
