package com.zzh.kuangshenshuo.forkJoin;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class test01 {
    public static void main(String[] args) {

    }

    /**
     * @param
     * @method 普通的写法
     */
    @Test
    public void test01() {
        Long num = 0l;
        long begin = System.currentTimeMillis();
        for (long i = 1l; i <= 10_0000_0000l; i++) {
            num = num + i;
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果为：" + num + "耗时：" + (end - begin));
    }

    static class forkJoinDemo extends RecursiveTask<Long> {
        private Long temp = 10000l;
        private Long begin;
        private Long end;
        private Long num = 0l;

        public forkJoinDemo(Long begin, Long end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if ((end - begin) < temp) {
                for (long i = begin; i <= end; i++) {
                    num = num + i;

                }
                return num;
            } else {
                long middle = (end + begin) / 2;
                forkJoinDemo fork1 = new forkJoinDemo(begin, middle);
                fork1.fork();
                forkJoinDemo fork2 = new forkJoinDemo(middle + 1, end);
                fork2.fork();
                return fork1.join() + fork2.join();
            }

        }
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long begin = System.currentTimeMillis();
        ForkJoinTask<Long> submit = forkJoinPool.submit(new forkJoinDemo(1l, 10_0000_0000l));
        long end = System.currentTimeMillis();
        System.out.println("计算结果为：" + submit.get() + "耗时：" + (end - begin));
    }

    @Test
    public void test03() {
        long begin = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(1l, 1000000000l).parallel().reduce(0l, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("计算结果为：" + reduce + "耗时：" + (end - begin));
    }
}

