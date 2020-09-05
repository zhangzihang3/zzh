package com.zzh.zzhThreedPool.custom;

import java.util.concurrent.*;

import static java.lang.System.out;

/**
 * @author ������
 * @class �̳߳��Ĵ�ܾ�����
 */
public class myThreadPoolExcute {
    public static void main(String[] args) {
        /**
         * AbortPolicy ���ﵽ�����������maximumPoolSize+workQueue��Ҳ����8��ʱ��
         * �����߳̽��������ᴦ������ˣ�ֱ���׳�һ���쳣
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
         * CallerRunsPolicy ���ﵽ�����������maximumPoolSize+workQueue��Ҳ����8��ʱ��
         * �����߳̽�����������߳������Ļ�����
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
             * DiscardOldestPolicy �������˵�ʱ�򣬳���ȥ������ľ����������׳��쳣
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
 * DiscardPolicy �������˵�ʱ�򣬶������񣬲����׳��쳣
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
