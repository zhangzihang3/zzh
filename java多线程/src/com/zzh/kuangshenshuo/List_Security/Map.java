package com.zzh.kuangshenshuo.List_Security;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Map {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
       //java.util.Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        //ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI),UUID.randomUUID().toString().substring(1, 5));
                System.out.println(map);
            }, "A").start();
        }
    }
}
