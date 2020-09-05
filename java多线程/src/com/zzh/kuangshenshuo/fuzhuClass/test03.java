package com.zzh.kuangshenshuo.fuzhuClass;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 张子行
 * @class Semaphore
 */
public class test03 {
    public static void main(String[] args) {

        //相当与有3个车位（资源）
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {

                    //相当于拿到许可证
                    semaphore.acquire();
                    System.out.println(finalI + "号车入库");
                    //Thread.sleep(2000); 线程不安全
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(finalI + "号车出库");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //收回许可证
                    semaphore.release();
                }
            }, "test").start();
        }
    }
}
