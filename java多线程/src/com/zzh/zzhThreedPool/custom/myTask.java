package com.zzh.zzhThreedPool.custom;

/**
 * @param
 * @function 线程任务
 * @return
 */
public class myTask implements Runnable {
    private int id;

    public myTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("线程:" + threadName + "即将执行任务" + id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程:" + threadName + "完成了任务" + id);

    }

    @Override
    public String toString() {
        return "myTask{" +
                "id=" + id +
                '}';
    }
}
