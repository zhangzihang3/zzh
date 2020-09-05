package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class robTicks {
    static class myRunAble implements Runnable {
        private static int ticks = 20;
        private int userName;

        public myRunAble(int name) {
            this.userName = name;
        }

        /**
         * @methods �������д�Լ�����Ʊҵ��
         */
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            /**
             * ��Ϊint ticks = 20��������myRunAble�У������е�new myRunAble()����
             * ����int ticks = 20������ԣ���Ϊ�˷�ֹһ��Ʊ��������ε��������myRunAble.class
             */
            synchronized (myRunAble.class) {
                if (ticks > 0) {
                    //ģ�����ݿ⣬����redis�Ĳ�������
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("�û���" + userName + "��" + name + "�ɹ�������" + ticks-- + "��Ʊ");

                } else {
                    System.out.println("�û���" + userName + "��" + name + "��Ʊʧ��");

                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            private int id = 1;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "����:" + id++);
            }
        });
        /**
         * ģ�����û���Ʊ����
         */
        for (int i = 1; i <= 30; i++) {
            executorService.submit(new myRunAble(i));
        }
        executorService.shutdown();
        executorService.submit(new myRunAble(88));
    }
}
