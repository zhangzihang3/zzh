package com.zzh.zzhThreedPool.custom;

import java.util.List;

/**
 * @param
 * @function 批量开启线程，并保存线程名字
 * @return
 */
public class myWork extends Thread {
    private List<Runnable> tasks;
    private String name;

    public myWork(List<Runnable> tasks, String name) {
        //设置线程的名字
        super(name);
        this.tasks = tasks;

    }

    /**
     * @param
     * @return
     * @function Thread.start也就是运行，thread中的run方法。这里相当与递归执行任务
     */
    @Override
    public void run() {
        while (tasks.size() > 0) {
            //这里应该是，获取index为0的任务，并且执行，执行后肯定要移除任务，这样才能递归执行
            Runnable r = tasks.remove(0);
            r.run();
        }
    }
}
