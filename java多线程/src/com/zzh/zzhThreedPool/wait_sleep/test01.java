package com.zzh.zzhThreedPool.wait_sleep;

public class test01 {
    public static void main(String[] args) {
//        Sleep();
        Wait();
    }

    public static void Wait() {

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    Thread.currentThread().wait(2000);
                    num--;
                    System.out.println(Thread.currentThread().getName() + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "wait").start();
        }
    }

    static volatile int num = 10;

    public static void Sleep() {
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    num--;
                    System.out.println(Thread.currentThread().getName() + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "sleep").start();
        }
    }
}
