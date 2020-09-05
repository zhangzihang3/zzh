package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @class newFixedThreadPool（）指定线程数量，及其对线程的创建进行干涉
 * 对于一些服务器不太行的，可以用这个指定，核心线程的数量，优化服务器的性能
 * 内部原理：
 * nThreads为我们传入的指定线程数，最大线程数与核心线程数相等，同理推其他的线程创建方式
 * new ThreadPoolExecutor(nThreads, nThreads,
 *                                       0L, TimeUnit.MILLISECONDS,
 *                                       new LinkedBlockingQueue<Runnable>());
 */
public class newFixedThreadPool {
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
        ExecutorService executorService = Executors.newFixedThreadPool(3);
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
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
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
        for (int i = 1; i <= 10; i++) {

            //提交线程池
            executorService.submit(new myRuable(i));
        }
    }

}
