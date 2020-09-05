package com.zzh.lock.spinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ������
 * @class ������
 */
public class spinLock {
    //int Ĭ��Ϊ0
    //Thread Ĭ��ֵΪnull
    private AtomicReference<Thread> reference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println("��ǰ�̼߳�����"+thread.getName());
        /**
         * ��һ���߳̽���ʱû�����������ڶ����߳̽�����ʱ�򣬵�һ���̻߳�û���ͷ������ڶ����߳̾ͻ�һֱ����
         */
        while (!reference.compareAndSet(null, thread)) {
            System.out.println("����");
        }
    }

    public void unMyLock() {
        Thread thread = Thread.currentThread();
        System.out.println("��ǰ�߳̽�����"+thread.getName());
        reference.compareAndSet(thread, null);
    }


}
