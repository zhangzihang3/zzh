package com.zzh.lock.spinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 张子行
 * @class 自旋锁
 */
public class spinLock {
    //int 默认为0
    //Thread 默认值为null
    private AtomicReference<Thread> reference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println("当前线程加锁："+thread.getName());
        /**
         * 第一个线程进来时没有自旋，当第二个线程进来的时候，第一个线程还没有释放锁，第二个线程就会一直自旋
         */
        while (!reference.compareAndSet(null, thread)) {
            System.out.println("自旋");
        }
    }

    public void unMyLock() {
        Thread thread = Thread.currentThread();
        System.out.println("当前线程解锁："+thread.getName());
        reference.compareAndSet(thread, null);
    }


}
