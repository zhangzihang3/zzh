package com.zzh.kuangshenshuo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class async {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * Ïàµ±ÓÚajax
         */
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"--ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("111");
        voidCompletableFuture.get();
        System.out.println("222");
    }
}
