package com.zzh.kuangshenshuo.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 张子行
 * @class offer（）和poll（），设置阻塞策略，超时自动释放
 * BlockingQueue.offer(String.valueOf("test"), 1, TimeUnit.MILLISECONDS)
 */
public class test04 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 指定队列长度，add remove 范围超出不会爆异常(LinkedBlockingDeque,ArrayBlockingQueue)
         */
        //BlockingDeque<Object> BlockingQueue = new LinkedBlockingDeque<>(3);
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(4);
        for (int i = 1; i <= 4; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("Thread:" + Thread.currentThread().getName() + "add-->" + queue.offer(String.valueOf(finalI)));
            }, String.valueOf(i)).start();
        }
        /**
         * 在等待的这2秒时间内，如果队列中有元素被取出来，则添加成功，否则添加失败自动释放
         */
        new Thread(() -> {
            try {
                System.out.println("Thread:" + Thread.currentThread().getName() + "add-->" + queue.offer(String.valueOf("test"), 1, TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("test")).start();
/**
 * 线程取值操作
 */
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println("Thread:" + Thread.currentThread().getName() + "get-->" + queue.poll());
            }, String.valueOf(i)).start();
        }

    }
}
