package com.zzh.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张子行
 * @class 经过lock锁处理 重入锁
 * 非公平锁：Lock lock = new ReentrantLock(); 线程间可以插队
 */
public class myTask2 implements Runnable {
    private int ticks = 100;
    Lock lock = new ReentrantLock();

    /**
     * @param
     * @return
     * @function 未做任何处理的卖票
     */
    @Override
    public void run() {

        //模拟死循环,卖票一直进行
        while (true) {
            //在可能出现线程安全的地方，加锁
            lock.lock();
            if (ticks > 0) {
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + ":正在出售第：" + ticks + "张票");
                    ticks--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //最终都将释放锁
                    lock.unlock();
                }

            }

        }
    }
}
