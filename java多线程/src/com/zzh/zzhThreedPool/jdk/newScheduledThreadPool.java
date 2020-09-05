package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.*;

/**
 * @param
 * @function newScheduledThreadPool()可以对线程的的执行时间干涉计划
 * @return
 */
public class newScheduledThreadPool {
    public static void main(String[] args) {
        //  test1();
        test2();
    }

    /**
     * @param
     * @function 静态内部类
     * @return
     */
    static class myRuable implements Runnable {
        private int id;

        public myRuable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "执行了任务" + id);
        }
    }

    /**
     * @param
     * @return
     * @function newCachedThreadPool()
     */
    public static void test1() {
        //创建jdk自带线程池
        ExecutorService executorService = Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 10; i++) {

            myRuable myRuable = new myRuable(i);
            //提交线程池
            executorService.submit(myRuable);
        }
    }

    /**
     * @param
     * @return
     * @function newCachedThreadPool(new ThreadFactory ())
     */
    public static void test2() {

        //创建jdk自带线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            private int n = 1;

            /**
             * @function 可以对线程进行一些属性的修改
             * @param
             * @return
             */
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "自定义线程池" + n++);

                return thread;
            }
        });


        /**
         * @param period 设置连续执行线程的间隔时间
         * @param initialDelay 初始化的延迟执行时间
         *
         */
        scheduledExecutorService.scheduleAtFixedRate(new myRuable(1), 1l, 1l, TimeUnit.SECONDS);

        System.out.println("over");


    }

}
