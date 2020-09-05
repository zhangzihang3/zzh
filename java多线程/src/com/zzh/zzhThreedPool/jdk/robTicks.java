package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class robTicks {
    static class myRunAble implements Runnable {
        private static int ticks = 20;
        private int userName;

        public myRunAble(int name) {
            this.userName = name;
        }

        /**
         * @methods 这里可以写自己的抢票业务
         */
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            /**
             * 因为int ticks = 20被包含于myRunAble中，且所有的new myRunAble()对象
             * 共享int ticks = 20这个属性，故为了防止一张票被卖出多次的情况，锁myRunAble.class
             */
            synchronized (myRunAble.class) {
                if (ticks > 0) {
                    //模拟数据库，及其redis的操作过程
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("用户：" + userName + "在" + name + "成功抢到了" + ticks-- + "号票");

                } else {
                    System.out.println("用户：" + userName + "在" + name + "抢票失败");

                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            private int id = 1;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "窗口:" + id++);
            }
        });
        /**
         * 模拟多个用户抢票场景
         */
        for (int i = 1; i <= 30; i++) {
            executorService.submit(new myRunAble(i));
        }
        executorService.shutdown();
        executorService.submit(new myRunAble(88));
    }
}
