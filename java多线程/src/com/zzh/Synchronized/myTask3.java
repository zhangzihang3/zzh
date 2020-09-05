package com.zzh.Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张子行
 * @class synchronized锁方法
 */
public class myTask3 implements Runnable {
    private int ticks = 100;

    /**
     * @param
     * @return
     * @function 未做任何处理的卖票
     */
    @Override
    public void run() {
        //模拟死循环,卖票一直进行
        while (true) {
            //sendTick();
            sendTick2();
        }
    }

    /**
     * @param
     * @return
     * @function 锁方法，同步方法
     */
    public synchronized void sendTick() {
        if (ticks > 0) {
            try {
                Thread.sleep(20);
                System.out.println(Thread.currentThread().getName() + ":正在出售第：" + ticks + "张票");
                ticks--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @param
     * @return
     * @function 锁对象this也就是Runnable，实现同步
     */
    public void sendTick2() {
        synchronized (this) {
            if (ticks > 0) {
                try {
                    Thread.sleep(20);
                    System.out.println(Thread.currentThread().getName() + ":正在出售第：" + ticks + "张票");
                    ticks--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
