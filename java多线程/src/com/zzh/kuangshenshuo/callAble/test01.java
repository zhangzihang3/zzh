package com.zzh.kuangshenshuo.callAble;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class test01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //适配类
        FutureTask futureTask = new FutureTask<>(new myCallAble());
        //只会打印一个callAble，结果会被缓存
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        //get()可能会造成阻塞，放到最后执行
        System.out.println(futureTask.get());
    }

    static class myCallAble implements Callable {
        private int a = 1;
        private int b = 1;

        @Override
        public Object call() throws Exception {
            System.out.println("callAble");
            return a + b;
        }
    }
}
