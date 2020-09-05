package com.zzh.zzhThreedPool.custom;

public class myThreadPoolTest {
    /**
     * @param
     * @return
     * @function 开启多个线程任务myTask（），根据情况加入到线程池中，
     */
    public static void main(String[] args) {
        myThreadPool myThreadPool = new myThreadPool(2, 4, 10);
        for (int i = 0; i < 30; i++) {
            //开启一个Runable
            myTask myTask = new myTask(i);
            myThreadPool.submit(myTask);
        }
    }
}
