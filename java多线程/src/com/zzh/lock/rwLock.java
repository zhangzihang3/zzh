package com.zzh.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 张子行
 * @class 读的时候可以多个线程读，写的时候只能一个线程写
 * 读读共存，读写不共存，写写不共存
 */
public class rwLock {
    private  static  volatile Map map = new HashMap<String, String>();
    //重入读写锁
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    //写锁
    private static Lock wLock = rwLock.writeLock();
    //读锁
    private static Lock rLock = rwLock.readLock();
    static void put(String key, String value) {
        try {
            wLock.lock();
            System.out.println("准备写入数据：" + key);
            Thread.sleep(200);
            map.put(key, value);
            System.out.println("成功写入数据：" + value + "...");
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            wLock.unlock();
        }
    }

    static void get(String key) {
        try {
            rLock.lock();
            System.out.println("准备读取数据：" + key);
            Thread.sleep(200);
            String value = (String) map.get(key);
            System.out.println("成功读取数据：" + value + "...");
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    put(i + "", i + "");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    get(i + "");
                }
            }
        }).start();
    }
}
