package com.zzh.kuangshenshuo.List_Security;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 张子行
 * @class list下的线程安全问题
 */
public class List {
    public static void main(String[] args) {
        /**
         * CopyOnWriteArrayList比Vector效率高，Vector使用的是内部加synchronized,
         */
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = new CopyOnWriteArrayList<>();
        java.util.List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1, 5));
                System.out.println(list);
            }, "A").start();
        }
    }
}
