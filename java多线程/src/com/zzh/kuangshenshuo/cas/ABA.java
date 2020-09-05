package com.zzh.kuangshenshuo.cas;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {
    private static int corePoolSize = 2;
    private static int maxPoolSize = 5;
    private static int keepLiveTime = 2;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static LinkedBlockingDeque workQueue = new LinkedBlockingDeque<>(3);
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    //策略
    private static ThreadPoolExecutor.DiscardOldestPolicy strategy = new ThreadPoolExecutor.DiscardOldestPolicy();

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                keepLiveTime,
                unit,
                workQueue,
                threadFactory,
                strategy);

        AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);
        for (int i = 1; i <= 3; i++) {
            pool.execute(new pr(money));
            pool.execute(new cs(money));
        }
    }

    static class pr implements Runnable {
        private AtomicStampedReference<Integer> money;

        public pr(AtomicStampedReference<Integer> money) {
            this.money = money;
        }

        @Override
        public void run() {
            while (true) {
                Integer ConcurrentMoney = money.getReference();
                if (ConcurrentMoney < 20) {
                    int stamp = money.getStamp();
                    boolean b = money.compareAndSet(ConcurrentMoney, money.getReference() + 20, stamp, money.getStamp() + 1);
                    if (b) {
                        System.out.println(Thread.currentThread().getName() + ":成功获得优惠补贴，当前金额为：" + money.getReference());
                        break;
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + ":你不符合获得优惠补贴的条件");
break;
                }
            }
        }
    }

    static class cs implements Runnable {
        private AtomicStampedReference<Integer> money;
        public cs(AtomicStampedReference<Integer> money) {
            this.money = money;
        }

        @Override
        public void run() {
            while (true) {
                Integer ConcurrentMoney = money.getReference();
                if (ConcurrentMoney > 10) {
                    int timeStamp = money.getStamp();
                    if (money.compareAndSet(ConcurrentMoney, money.getReference()-10, timeStamp, money.getStamp() + 1)) {
                        System.out.println(Thread.currentThread().getName() + ":成功消费10元，当前还有" + money.getReference() + "元");
                        break;
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "卡里的钱不足10快");
                    break;
                }
            }
        }
    }

}
