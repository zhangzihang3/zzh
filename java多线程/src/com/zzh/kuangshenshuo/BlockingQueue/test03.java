package com.zzh.kuangshenshuo.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 张子行 put组合take
 * @class 一直阻塞的队列
 */
public class test03 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 指定队列长度，put组合take 范围超出不会爆异常(LinkedBlockingDeque,ArrayBlockingQueue)会阻塞
         */
        //BlockingDeque<Object> BlockingQueue = new LinkedBlockingDeque<>(3);
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);
        //相当与添加
        queue.put("a");
        queue.put("b");
        queue.put("c");
        System.out.println("====================+");
        //队列没有位置了，会一直阻塞
        queue.put("d");
        System.out.println("====================-");
        //相当于取出
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        //同理队列中没有元素也会一直阻塞
        //System.out.println(BlockingQueue.take());
    }
}
