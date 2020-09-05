package com.zzh.Synchronized;

/**
 * @author 张子行
 * @class 未经过处理的
 */
public class myTask implements Runnable {
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
            if (ticks > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":正在出售第：" + ticks + "张票");
                ticks--;
            }
        }
    }
}
