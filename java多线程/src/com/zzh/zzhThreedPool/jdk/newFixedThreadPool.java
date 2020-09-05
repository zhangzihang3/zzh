package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @class newFixedThreadPool����ָ���߳�������������̵߳Ĵ������и���
 * ����һЩ��������̫�еģ����������ָ���������̵߳��������Ż�������������
 * �ڲ�ԭ��
 * nThreadsΪ���Ǵ����ָ���߳���������߳���������߳�����ȣ�ͬ�����������̴߳�����ʽ
 * new ThreadPoolExecutor(nThreads, nThreads,
 *                                       0L, TimeUnit.MILLISECONDS,
 *                                       new LinkedBlockingQueue<Runnable>());
 */
public class newFixedThreadPool {
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
        ExecutorService executorService = Executors.newFixedThreadPool(3);
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
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
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
        for (int i = 1; i <= 10; i++) {

            //�ύ�̳߳�
            executorService.submit(new myRuable(i));
        }
    }

}
