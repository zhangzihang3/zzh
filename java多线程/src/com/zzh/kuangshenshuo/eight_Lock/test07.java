package com.zzh.kuangshenshuo.eight_Lock;

import java.util.concurrent.TimeUnit;

public class test07 {
    public static void main(String[] args) {
        data data1 = new data();
        data data2 = new data();
        new Thread(() -> {
            data1.printA();
        }, "A").start();
        new Thread(() -> {
            data2.printB();
        }, "B").start();

        //data.printB(); ��ִ�У���Ȼ������2��,data1��ס����data����data2��������data��class��
        // �����ķ�Χ��һ�£����A�����ߺ�ִ��
        //data.printA(); ��ִ��
    }

    static class data {
        /**
         * @method synchronized�����ǵ�����������Ķ���
         */
        public static synchronized void printA() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * @method ������
         */
        public static synchronized void printB() {
            System.out.println("B");
        }

    }
}
