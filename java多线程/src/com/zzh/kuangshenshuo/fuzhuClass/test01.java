package com.zzh.kuangshenshuo.fuzhuClass;

import java.util.concurrent.CountDownLatch;

/**
 * @author 张子行
 * @class CountDownLatch的使用
 */
public class test01 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("hello");
                countDownLatch.countDown();
            }, "a").start();
        }
        //当计数器为0的时候，才向下执行
        countDownLatch.await();
        System.out.println("over");


    }
}
