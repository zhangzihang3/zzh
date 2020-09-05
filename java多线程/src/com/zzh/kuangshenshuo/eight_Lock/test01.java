package com.zzh.kuangshenshuo.eight_Lock;

public class test01 {
    public static void main(String[] args) {
        data data = new data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.pr();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.cs();
            }
        }, "A").start();

    }

    static class data {
        private static int num = 0;

        /**
         * @method 消费者
         */
        public synchronized void pr() {
            if (num == 0) {
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
            if (num != 0) {
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
