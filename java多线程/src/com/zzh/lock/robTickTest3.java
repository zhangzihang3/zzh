package com.zzh.lock;


import com.zzh.lock.myTask2;

public class robTickTest3 {
    /**
     * @param
     * @return
     * @function �൱�ڶ�����ڣ���ͬһ��Ʊ
     */


    public static void main(String[] args) {
        /**
         * ������ͬһ��myTask
         */
        myTask3 myTask = new myTask3();
        Thread thread = new Thread(myTask);
        Thread thread2 = new Thread(myTask);
        Thread thread3 = new Thread(myTask);
        Thread thread4 = new Thread(myTask);
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
