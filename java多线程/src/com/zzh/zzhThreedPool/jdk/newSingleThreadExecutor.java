package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @param
 * @function newSingleThreadExecutor（）单线程，安全性高
 * @return
 */
public class newSingleThreadExecutor {
    public static void main(String[] args) {
        //      test1();
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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            private int n = 1;

            /**
             * @method 可以对线程进行一些属性的修改
             */
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "自定义线程池" + n++);
                return thread;
            }
        });
        for (int i = 1; i <= 10; i++) {
            //提交线程池
            executorService.submit(new myRuable(i));
        }
    }

}
