package com.zzh.kuangshenshuo.eight_Lock;

public class test05 {
    public static void main(String[] args) {
        data data = new data();
        new Thread(() -> {
            data.printA();
        }, "A").start();
        new Thread(() -> {
            data.printB();
        }, "B").start();
        new Thread(() -> {
            data.printC();
        }, "C").start();
        //data.printC(); 先执行,因为data.printC()不受syschronized控制
        //data.printA(); 接着执行，虽然休眠了2秒,但是锁住了data对象,根据执行上下文顺序执行
        //data.printB(); 后执行
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

        public void printC() {
            System.out.println("C");
        }
    }
}
