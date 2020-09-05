package com.zzh.kuangshenshuo.eight_Lock;

import java.util.concurrent.TimeUnit;

public class test02 {
    public static void main(String[] args) {
        data data1 = new data();
        data data2 = new data();
        new Thread(()->{
            data1.printA();
        },"A").start();
        new Thread(()->{
            data2.printB();
        },"B").start();

        //data.printA(); 先执行，虽然休眠了2秒,但是锁住了data的class,故还是根据执行上下文顺序执行
        //data.printB(); 后执行
    }

    static  class data {
        /**
         * @method synchronized锁的是data.class
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
