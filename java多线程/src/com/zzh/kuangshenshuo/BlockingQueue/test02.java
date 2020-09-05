package com.zzh.kuangshenshuo.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 张子行 offer组合poll空参
 * @class 超出范围就会报错的队列
 */
public class test02 {
    public static void main(String[] args) {
        /**
         * 指定队列长度，offer组合poll 范围超出不会爆异常(LinkedBlockingDeque,ArrayBlockingQueue)
         */
        //BlockingDeque<Object> BlockingQueue = new LinkedBlockingDeque<>(3);
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(4);
        //相当与添加，添加成功为true，添加失败为false
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));
        //取出队首元素，
        System.out.println(queue.peek());
        //相当于取出，当没有值可取的时候，取出来的值为null
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //当没有队首元素时，取出的值是空的
        System.out.println(queue.peek());
    }

}
