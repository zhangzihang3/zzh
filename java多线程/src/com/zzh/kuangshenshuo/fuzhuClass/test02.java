package com.zzh.kuangshenshuo.fuzhuClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 张子行
 * @class CyclicBarrier使用
 */
public class test02 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("收集7颗龙珠成功");
        });
        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("收集到"+ finalI +"颗龙珠");
                try {
                    //当等待的线程数达到了7个，就会打印"收集7颗龙珠成功"，且后面的线程会接着等待，
                    // 如果达不到7个线程，那么会一直等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "a").start();
        }
    }
}
