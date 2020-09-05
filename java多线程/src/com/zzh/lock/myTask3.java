package com.zzh.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ������
 * @class ����lock������ ������
 * ��ƽ����Lock lock = new ReentrantLock(); �̼߳䲻���Բ��
 */
public class myTask3 implements Runnable {
    private int ticks = 100;
    Lock lock = new ReentrantLock(true);

    /**
     * @param
     * @return
     * @function δ���κδ������Ʊ
     */
    @Override
    public void run() {
        //ģ����ѭ��,��Ʊһֱ����
        while (true) {
            //�ڿ��ܳ����̰߳�ȫ�ĵط�������
            lock.lock();
            if (ticks > 0) {
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + ":���ڳ��۵ڣ�" + ticks + "��Ʊ");
                    ticks--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //���ն����ͷ���
                    lock.unlock();
                }

            }

        }
    }
}
