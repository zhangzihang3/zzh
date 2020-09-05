package com.zzh.kuangshenshuo.fuzhuClass;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ������
 * @class Semaphore
 */
public class test03 {
    public static void main(String[] args) {

        //�൱����3����λ����Դ��
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {

                    //�൱���õ����֤
                    semaphore.acquire();
                    System.out.println(finalI + "�ų����");
                    //Thread.sleep(2000); �̲߳���ȫ
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(finalI + "�ų�����");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //�ջ����֤
                    semaphore.release();
                }
            }, "test").start();
        }
    }
}
