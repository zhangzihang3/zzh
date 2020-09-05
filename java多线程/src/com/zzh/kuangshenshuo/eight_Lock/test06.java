package com.zzh.kuangshenshuo.eight_Lock;

public class test06 {
    public static void main(String[] args) {
        data data1 = new data();
        data data2 = new data();
        new Thread(() -> {
            data1.printA();
        }, "A").start();
        new Thread(() -> {
            data2.printB();
        }, "B").start();
        //data.printB(); 先执行,因为syschronized锁的是class,且data1.printA()休眠了2秒
        //data.printA(); 后执行

    }

    static class data {
        /**
         * @method synchronized锁的是data.class
         */
        public synchronized void printA() {
            try {
                Thread.sleep(2000);
                System.out.println("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * @method 生产者
         */
        public synchronized void printB() {
            System.out.println("B");

        }
    }
}
