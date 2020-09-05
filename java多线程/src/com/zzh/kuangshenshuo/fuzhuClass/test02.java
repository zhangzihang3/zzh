package com.zzh.kuangshenshuo.fuzhuClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ������
 * @class CyclicBarrierʹ��
 */
public class test02 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("�ռ�7������ɹ�");
        });
        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("�ռ���"+ finalI +"������");
                try {
                    //���ȴ����߳����ﵽ��7�����ͻ��ӡ"�ռ�7������ɹ�"���Һ�����̻߳���ŵȴ���
                    // ����ﲻ��7���̣߳���ô��һֱ�ȴ�
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "a").start();
        }
    }
}
