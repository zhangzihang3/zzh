package com.zzh.kuangshenshuo.fuzhuClass;

import java.util.concurrent.CountDownLatch;

/**
 * @author ������
 * @class CountDownLatch��ʹ��
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
        //��������Ϊ0��ʱ�򣬲�����ִ��
        countDownLatch.await();
        System.out.println("over");


    }
}
