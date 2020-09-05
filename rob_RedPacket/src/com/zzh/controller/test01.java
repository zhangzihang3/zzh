package com.zzh.controller;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class test01 {
    //public static int people = 10;
    //红包数量
    public static int redPacketNum = 10;
    //红包金额
    public static int money = 10;
    //cpu密集型确定核心线程数量
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
            corePoolSize + 1,
            3,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Runnable task = () -> {
                if (money > 0) {

                }
            };

        }
    }

}
