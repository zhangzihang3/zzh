package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.*;

public class Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> f = executorService.submit(new myCallAble(1, 2));
        testFuture(f);
        boolean b = f.cancel(true);
        System.out.println("取消任务执行的结果:"+b);
        Integer v = f.get(1, TimeUnit.SECONDS);//由于等待时间过短,任务来不及执行完成,会报异常
        System.out.println("任务执行的结果是:"+v);
    }

    public static void testFuture(Future<Integer> f) throws ExecutionException, InterruptedException {
        System.out.println("第一次判断任务是否取消:"+f.isCancelled());
        System.out.println("第一次判断任务是否关闭:"+f.isDone());
        //get()一直等待任务的执行，直到结束
        Integer result = f.get();
        System.out.println("任务执行的结果为："+result);
        System.out.println("第二次判断任务是否取消:"+f.isCancelled());
        System.out.println("第二次判断任务是否关闭:"+f.isDone());

    }

    static class myCallAble implements java.util.concurrent.Callable<Integer> {
        private int i;
        private int j;

        public myCallAble(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public Integer call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备开始计算");
            int result = i + j;
            Thread.sleep(2000);
            System.out.println(name + "计算完成");
            return result;
        }
    }
}
