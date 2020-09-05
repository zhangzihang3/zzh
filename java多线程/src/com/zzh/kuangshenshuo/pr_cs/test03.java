package com.zzh.kuangshenshuo.pr_cs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test03 {
    public static void main(String[] args) {
        data data = new data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();

    }

    static class data {
        private static int num = 0;
        private static Lock lock = new ReentrantLock();
        private static Condition condition1 = lock.newCondition();
        private static Condition condition2 = lock.newCondition();
        private static Condition condition3 = lock.newCondition();


        /**
         * @method 生产者
         */
        public void printA() {
            lock.lock();
            try {
                while (num != 0) {
                    condition1.await();
                }
                num = 1;
                System.out.println(Thread.currentThread().getName() + "===>A");
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * @method 生产者
         */
        public  void printB() {
            lock.lock();
            try {
                while (num != 1) {
                    condition2.await();
                }
                num = 2;
                System.out.println(Thread.currentThread().getName() + "===>" + "B");
                condition3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * @method 生产者
         */
        public  void printC() {
            lock.lock();
            try {
                while(num != 2) {
                    condition3.await();
                }
                num = 0;
                System.out.println(Thread.currentThread().getName() + "===>" + "C");
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
