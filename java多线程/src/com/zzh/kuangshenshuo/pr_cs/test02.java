package com.zzh.kuangshenshuo.pr_cs;

public class test02 {
    public static void main(String[] args) {
        data data = new data();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.pr();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.cs();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.pr();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.cs();
            }
        }, "D").start();

    }

    static class data {
        private static int num = 0;

        /**
         * @method 消费者
         */
        public synchronized void pr() {
            /**
             * 注意这里判断用while
             * 原因：就是用if判断的话，唤醒后线程会从wait之后的代码开始运行，
             * 但是不会重新判断if条件，直接继续运行if代码块之后的代码，
             * 而如果使用while的话，也会从wait之后的代码运行，但是唤醒后会重新判断循环条件，
             * 如果不成立再执行while代码块之后的代码块，成立的话继续wait。
             */
            while (num == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "===>" + num);
            this.notifyAll();
        }

        /**
         * @method 生产者
         */
        public synchronized void cs() {
            while (num != 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "===>" + num);
            this.notifyAll();

        }
    }
}
