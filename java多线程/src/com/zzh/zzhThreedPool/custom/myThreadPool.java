package com.zzh.zzhThreedPool.custom;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @param
 * @function 线程池
 * @return
 */
public class myThreadPool {
    // 1:任务队列   集合  需要控制线程安全问题
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());
    //2:当前线程数量
    private int num;
    //3:核心线程数量
    private int corePoolNum;
    //4:最大线程数量
    private int maxThreadNum;
    /**
     * @function 任务队列长度的计算（核心线程数/单个线程执行的时间*2）
     * 一般单个线程执行的时间为0.1秒，例如10个线程，任务队列可以设置成200
     * @param
     * @return
     */
    private int workSize;

    public myThreadPool(int corePoolNum, int maxThreadNum, int workSize) {
        this.corePoolNum = corePoolNum;
        this.maxThreadNum = maxThreadNum;
        this.workSize = workSize;
    }

    public void submit(Runnable r) {
        //注意这里是tasks（任务数）的长度大于，任务队列的长度，不是num>= workSize
        if (tasks.size() >= workSize) {
            System.out.println("任务:" + r + "被丢弃了");
        } else {
            //线程没有超过最大线程数，添入tasks（线程任务）
            tasks.add(r);
            //开启线程
            excuteTask(r);
        }
    }

    public void excuteTask(Runnable r) {
        if (num < corePoolNum) {
            //可以编写自己的业务实现类,创建线程
            new myWork(tasks, "核心线程:" + num).start();
            num++;
        } else if (num < maxThreadNum) {
            //可以编写自己的业务实现类,创建线程
            new myWork(tasks, "非核心线程:" + num).start();
            num++;
        } else {
            //可以编写自己的业务实现类,创建线程
            new myWork(tasks, "任务:" + num + "被缓存了").start();
        }

    }
}
