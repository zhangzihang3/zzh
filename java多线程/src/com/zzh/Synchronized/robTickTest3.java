package com.zzh.Synchronized;


public class robTickTest3 {
    /**
     * @param
     * @return
     * @function 相当于多个窗口，卖同一种票
     */


    public static void main(String[] args) {
        /**
         * 这里是同一个myTask
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
