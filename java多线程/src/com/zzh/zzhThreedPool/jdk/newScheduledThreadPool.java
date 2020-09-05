package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.*;

/**
 * @param
 * @function newScheduledThreadPool()���Զ��̵߳ĵ�ִ��ʱ�����ƻ�
 * @return
 */
public class newScheduledThreadPool {
    public static void main(String[] args) {
        //  test1();
        test2();
    }

    /**
     * @param
     * @function ��̬�ڲ���
     * @return
     */
    static class myRuable implements Runnable {
        private int id;

        public myRuable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "ִ��������" + id);
        }
    }

    /**
     * @param
     * @return
     * @function newCachedThreadPool()
     */
    public static void test1() {
        //����jdk�Դ��̳߳�
        ExecutorService executorService = Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 10; i++) {

            myRuable myRuable = new myRuable(i);
            //�ύ�̳߳�
            executorService.submit(myRuable);
        }
    }

    /**
     * @param
     * @return
     * @function newCachedThreadPool(new ThreadFactory ())
     */
    public static void test2() {

        //����jdk�Դ��̳߳�
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            private int n = 1;

            /**
             * @function ���Զ��߳̽���һЩ���Ե��޸�
             * @param
             * @return
             */
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "�Զ����̳߳�" + n++);

                return thread;
            }
        });


        /**
         * @param period ��������ִ���̵߳ļ��ʱ��
         * @param initialDelay ��ʼ�����ӳ�ִ��ʱ��
         *
         */
        scheduledExecutorService.scheduleAtFixedRate(new myRuable(1), 1l, 1l, TimeUnit.SECONDS);

        System.out.println("over");


    }

}
