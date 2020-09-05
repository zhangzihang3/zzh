package com.zzh.kuangshenshuo.BlockingQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * @author 张子行 add组合remove
 * @class 超出范围就会报错的队列
 */
public class test01 {
    public static void main(String[] args) {
        /**
         * 指定队列长度，add组合remove 范围超出会爆异常(LinkedBlockingDeque,ArrayBlockingQueue)
         */
         BlockingDeque<Object> queue = new LinkedBlockingDeque<>(3);
        //ArrayBlockingQueue<Object> BlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        //检测队首元素
        System.out.println(queue.element());
        //System.out.println(BlockingQueue.add("d"));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //当没有队首元素时，会报出异常
        System.out.println(queue.element());
        //System.out.println(BlockingQueue.remove());
    }
}
