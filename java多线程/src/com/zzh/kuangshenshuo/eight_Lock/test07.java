package com.zzh.kuangshenshuo.eight_Lock;

import java.util.concurrent.TimeUnit;

public class test07 {
    public static void main(String[] args) {
        data data1 = new data();
        data data2 = new data();
        new Thread(() -> {
            data1.printA();
        }, "A").start();
        new Thread(() -> {
            data2.printB();
        }, "B").start();

        //data.printB(); 先执行，虽然休眠了2秒,data1锁住的是data对象。data2锁定的是data的class，
        // 锁定的范围不一致，因此A有休眠后执行
        //data.printA(); 后执行
    }

    static class data {
        /**
         * @method synchronized锁的是调用这个方法的对象
         */
        public static synchronized void printA() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * @method 生产者
         */
        public static synchronized void printB() {
            System.out.println("B");
        }

    }
}
