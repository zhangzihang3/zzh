package com.zzh.zzhThreedPool.custom;

import java.util.concurrent.*;

import static java.lang.System.out;

/**
 * @author 张子行
 * @class 线程池四大拒绝策略
 */
public class myThreadPoolExcute {
    public static void main(String[] args) {
        /**
         * AbortPolicy 当达到了最大任务数maximumPoolSize+workQueue，也就是8的时候，
         * 还有线程进来，不会处理这个人，直接抛出一个异常
         */
        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        /**
         * CallerRunsPolicy 当达到了最大任务数maximumPoolSize+workQueue，也就是8的时候，
         * 还有线程进来，让这个线程哪来的回哪里
         */
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
            /**
             * DiscardOldestPolicy 对列满了的时候，尝试去和最早的竞争，不会抛出异常
             */
        ThreadPoolExecutor pool3 = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
/**
 * DiscardPolicy 对列满了的时候，丢掉任务，不会抛出异常
 */
        ThreadPoolExecutor pool4 = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 1; i <= 9; i++) {
                pool3.execute(() -> {
                    out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            out.println(e);
        } finally {
            pool3.shutdown();
        }


    }

}
